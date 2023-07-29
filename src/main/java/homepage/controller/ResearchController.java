package homepage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import homepage.entity.ResearchEntity;
import homepage.form.ResearchForm;
import homepage.service.ResearchService;

@Controller
public class ResearchController {

	@Autowired
	ResearchService researchService;
	
	@RequestMapping("/research")
    public String research(HttpServletRequest request, ResearchForm form, Model model) {
		HttpSession session = request.getSession();
		
		Boolean isAdmin =false;
		if(session.getAttribute("admin") !=null && session.getAttribute("admin").equals("admin")) {
			isAdmin = true;
		}
		model.addAttribute("isAdmin", isAdmin);
		
		ResearchEntity research = researchService.selectResearch();
		model.addAttribute("research", research);
		
		model.addAttribute("form", form); 
        return "/research";
    }
	
	@RequestMapping("/updateResearch")
    public String updateResearch(HttpServletRequest request, ResearchForm form) {	
		ResearchEntity research = new ResearchEntity();
		
		research.setContent(form.getContent());
		
		if(form.getSeq() == 0) {
			researchService.insertResearch(research);
		} else {
			research.setSeq(form.getSeq());
			researchService.updateResearch(research);
		}		
        return "redirect:/research";
    }
	
	@RequestMapping("/deleteResearch")
    public String deleteNews(HttpServletRequest request, ResearchForm form) {	
		
		if(form.getSeq() != 0) {
			int seq = form.getSeq();
			researchService.deleteResearchBySeq(seq);
		}
        return "redirect:/research";
    }
}
