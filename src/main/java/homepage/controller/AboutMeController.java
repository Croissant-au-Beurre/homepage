package homepage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import homepage.form.AboutMeForm;
import homepage.service.AboutMeService;
import homepage.entity.NewsEntity;

@Controller
public class AboutMeController {
	
	@Autowired
	AboutMeService aboutMeService;

	@RequestMapping("/about_me")
    public String aboutMe(HttpServletRequest request, AboutMeForm form, Model model) {
		HttpSession session = request.getSession();
		
		Boolean isAdmin =false;
		if(session.getAttribute("admin") !=null && session.getAttribute("admin").equals("admin")) {
			isAdmin = true;
		}
		model.addAttribute("isAdmin", isAdmin);
		
		List<NewsEntity>newsList = aboutMeService.selectAllNews();
		for (NewsEntity news : newsList) {
		    Date insDate = news.getInsDate();
		    if(insDate != null) {
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    String dateString = formatter.format(insDate);
			    news.setFormattedDate(dateString);
		    }		    
		}		   		   
		model.addAttribute("newsList", newsList);
		
		model.addAttribute("form", form); 
        return "/about_me";
    }
	
	@RequestMapping("/insertNews")
    public String insertNews(HttpServletRequest request, AboutMeForm form) {	
		NewsEntity news = new NewsEntity();
		
		news.setInsDate(form.getInsDate());
		news.setNews(form.getNews());		
		if(form.getUrl() != null) {
			news.setUrl(form.getUrl());			
		}
		if(form.getSeq() == 0) {
			aboutMeService.insertNews(news);
		} else {
			news.setSeq(form.getSeq());
			aboutMeService.updateNews(news);
		}		
        return "redirect:/about_me";
    }
	
	@RequestMapping("/deleteNews")
    public String deleteNews(HttpServletRequest request, AboutMeForm form) {	
		
		if(form.getSeq() != 0) {
			int seq = form.getSeq();
			aboutMeService.deleteNewsBySeq(seq);
		}
        return "redirect:/about_me";
    }
}

