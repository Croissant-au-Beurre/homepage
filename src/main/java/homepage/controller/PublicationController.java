package homepage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import homepage.entity.NewsEntity;
import homepage.entity.PublicationEntity;
import homepage.form.AboutMeForm;
import homepage.form.PublicationForm;
import homepage.service.PublicationService;


@Controller
public class PublicationController {

	@Autowired
	PublicationService publicationService;
	
	@RequestMapping("/publication")
    public String publication(HttpServletRequest request, PublicationForm form, Model model) {
		HttpSession session = request.getSession();
		
		Boolean isAdmin =false;
		if(session.getAttribute("admin") !=null && session.getAttribute("admin").equals("admin")) {
			isAdmin = true;
		}
		model.addAttribute("isAdmin", isAdmin);
		int fromDetailFlg = 0;
		if(session.getAttribute("fromDetailFlg") != null) {
			try {
			    fromDetailFlg = Integer.parseInt(session.getAttribute("fromDetailFlg").toString());
			} catch (NumberFormatException e) {
			    // 或者给一个默认值
			    fromDetailFlg = 0;
			}
		}
		
		if(fromDetailFlg != 0) {
			model.addAttribute("fromDetailFlg", fromDetailFlg);
		}
		
		List<PublicationEntity> publicationList = publicationService.selectAllPublications();
		model.addAttribute("publicationList", publicationList);
		
		model.addAttribute("form", form); 
		
        return "/publication";
    }
	
	@RequestMapping("/insertPublication")
    public String insertNews(HttpServletRequest request, PublicationForm form) {	
		PublicationEntity publication = new PublicationEntity();
		
		publication.setAuteurs(form.getAuteurs());
		publication.setTitle(form.getTitle());
		publication.setDoi(form.getDoi());
		if(form.getUrl() != null) {
			publication.setUrl(form.getUrl());			
		}
		if(form.getSeq() == 0) {
			publicationService.insertPublication(publication);
		} else {
			publication.setSeq(form.getSeq());
			publicationService.updatePublication(publication);
		}
		HttpSession session = request.getSession();
		session.setAttribute("fromDetailFlg", form.getSeq());
        return "redirect:/publication";
    }
	
	@RequestMapping("/deletePublication")
    public String deleteNews(HttpServletRequest request, PublicationForm form) {	
		
		if(form.getSeq() != 0) {
			int seq = form.getSeq();
			publicationService.deletePublicationBySeq(seq);
		}
        return "redirect:/publication";
    }
}
