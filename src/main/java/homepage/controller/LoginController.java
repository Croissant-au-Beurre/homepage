package homepage.controller;


import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import homepage.entity.LoginEntity;
import homepage.form.LoginForm;
import homepage.service.EmailService;
import homepage.service.LoginService;


@Controller
public class LoginController {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	LoginService loginService;
	
	private String verificationCode = generateVerificationCode();
	
	@RequestMapping("/login")
    public String login(Model model, LoginForm form) {
		
		
		model.addAttribute("form", form); // 添加表单对象到模型中
		return "/login";
		
	}
	
	@RequestMapping("/excuteLogin")
    public String excuteLogin(Model model, LoginForm form) {
		model.addAttribute("form", form); // 添加表单对象到模型中
		LoginEntity loginEntity = loginService.selectUserInfo();
		
		if(form.getId() !=null && form.getPassword() != null) {
			if(form.getId().equals(loginEntity.getId()) && form.getPassword().equals(loginEntity.getPassword())) {	
				// 发送验证码邮件
		        emailService.sendVerificationCode("icywoods.1@gmail.com", verificationCode);
		        System.out.println(verificationCode);
		        return "/verify"; // 验证成功，重定向到验证码页面
			} else {
				// 验证失败，显示提示框
			    return "redirect:/login?error"; // 重定向到登录页面，并在URL中加入错误参数
			}
		} else {
			return "redirect:/login?blank";
		}
		
	}
	
	@RequestMapping("/verify")
	    public String verify(HttpServletRequest request, LoginForm form) {
			HttpSession session = request.getSession();

			// 在此验证验证码的逻辑
	        if (form.getVerificationCode().equals(verificationCode)) {        	
	        	session.setAttribute("admin", "admin");	        	 
	            return "redirect:/about_me"; 
	        } else {
	            return "redirect:/entrance"; // 验证失败，重新加载验证码页面
	        }
	    }
	
	private String generateVerificationCode() {
        
		// 在这里生成验证码的逻辑，可以使用随机数生成器等方法
		String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    int CODE_LENGTH = 6;
	    
	    Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
//		logout  
//
//		CookieUtil.deleteCookieValue(response, "lastAccessTime", "0");
//
//		// ログイン情報と検索条件もクリア
//		var k=request.getSession().getAttributeNames().asIterator();
//		while(k.hasNext()) {
//		request.getSession().removeAttribute( k.next());
//		}
//
//		login
//		  CookieUtil.setCookieValue(response, "sessionCut", Constants.FLG_1, null);
//
//		//ログイン成功フラグ
//		CookieUtil.setCookieValue(response, "loginsuccess", "1", -1);
//
//		/**
//		* セッションタイムリセット処理
//		* @param model モデル
//		* @param form ログインフォーム
//		* @return ログイン画面
//		*/
//		@PostMapping("relogin")
//		@ResponseBody
//		public String relogin(
//		Model model,
//		HttpServletRequest request,
//		String employeeNo,
//		String password,
//		String lastAccessTime
//		) {
//		String jsonStr = "[]";
//
//		LoginForm form = new LoginForm();
//		form.setEmployeeNo(employeeNo);
//		form.setPassword(password);
//
//		Login login = RequestUtil.getUserInfo(request);
//
//		if(login != null) {
//		//レスポンス
//		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//
//		//ログインの処理
//		Syain syain = syainService.loginProc(form, appProperties.getJinjiLoginFlg().toString());
//
//		if (syain == null) {
//		//担当者コードがnull又は空文字列の場合
//		jsonStr = "{\"state\":\"1\",\"message\":\"IDまたはパスワードが間違っています。\"}";
//
//		}else {
//
//		//現在の時刻
//		long currentTime = System.currentTimeMillis();
//		CookieUtil.setCookieValue(response, "lastAccessTime", String.valueOf(currentTime), -1);
//
//		jsonStr = "{\"state\":\"0\",\"message\":\"ログインが継続されました。\"}";
//		}
//		}else {
//		jsonStr = "{\"state\":\"1\",\"message\":\"セッションが継続できません。ログイン画面から再度ログインしてください。\"}";
//		}
//
//		return jsonStr;
//		}
//		}
        
    
}
