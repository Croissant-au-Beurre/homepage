package homepage.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import homepage.entity.BlogEntity;
import homepage.form.BlogForm;
import homepage.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/blog")
    public String blog(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Boolean isAdmin =false;
		if(session.getAttribute("admin") !=null && session.getAttribute("admin").equals("admin")) {
			isAdmin = true;
		}
		model.addAttribute("isAdmin", isAdmin);
		List<BlogEntity> blogList = blogService.selectAllBlog();
		model.addAttribute("blogList", blogList);
        return "/blog";
    }
	
	/**
	 * Blog显示页面
	 * 
	 * @param model   
	 * @param request 
	 * @param form    
	 * @return
	 */
	@RequestMapping(value = "/blog_detail/{seq}")
	public String detail( Model model,
			HttpServletRequest request,
			BlogForm form, @PathVariable(name = "seq", required = false) int seq) {
		
		HttpSession session = request.getSession();		
		Boolean isAdmin =false;
		if(session.getAttribute("admin") !=null && session.getAttribute("admin").equals("admin")) {
			isAdmin = true;
		}
		model.addAttribute("isAdmin", isAdmin);
		form = blogService.selectBlogToForm(seq);
		model.addAttribute("form", form);
		return "blog_detail";

	}
	
	/**
	 * Blog编辑页面
	 * 
	 * @param model   
	 * @param request 
	 * @param form    
	 * @return
	 */
	@RequestMapping(value = "/blog_detail")
	public String detail( Model model,
			HttpServletRequest request,
			BlogForm form) {

		HttpSession session = request.getSession();		
		Boolean isAdmin =false;
		if(session.getAttribute("admin") !=null && session.getAttribute("admin").equals("admin")) {
			isAdmin = true;
		}
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("form", form);
		return "blog_detail";

	}
	
	/**
	 * Blog新建或编辑
	 * 
	 * @param model   
	 * @param request 
	 * @param form    
	 * @return
	 */
	@PostMapping(value = "/regist", params = "regist")
	public String regist( Model model,
			HttpServletRequest request,
			BlogForm form) {
		HttpSession session = request.getSession();
		if(session.getAttribute("imgPath") != null) {
			form.setImgPath((session.getAttribute("imgPath")).toString());
			session.removeAttribute("imgPath");
		}
		int seq = blogService.regist(form, request);
		return "redirect:/blog_detail/" + seq;
	}

	/**
	 * Blog删除
	 * 
	 * @param model   
	 * @param request 
	 * @param form    
	 * @return
	 */
	@PostMapping(value = "/regist", params = "delete")
	public String delete(Model model, HttpServletRequest request, BlogForm form) {
		String strRegist = "deleted";
		int id = blogService.delete(form, request);
		return "redirect:/blog";
	}
	
	@RequestMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        // 指定文件夹的路径
        String folderPath1 = "E:/forProgramme/workplace/homepage/src/main/resources/static";
        String folderPath2 = "/images/blog/" + 1;
        String folderPath = folderPath1 + folderPath2;
        // 创建文件夹
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        
        try {
            // 保存上传的文件到指定文件夹
            file.transferTo(new File(folderPath + "/" + file.getOriginalFilename()));
            HttpSession session = request.getSession();
            String imgPath = (folderPath2 + "/" + file.getOriginalFilename()).toString();
            session.setAttribute("imgPath", imgPath);
            return "/blog";
        } catch (IOException e) {
            e.printStackTrace();
            return "/blog";
        }
    }
}
