package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) 
	throws Exception {
		// 폼에 전송한 id와 password 파라미터 값을 구한다
		String id = trim(req.getParameter("id"));
		String password = trim(req.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("error", errors);
		
		if(id == null || id.isEmpty())
			errors.put("id", Boolean.TRUE);
		if(password == null || password.isEmpty())
			errors.put("password", Boolean.TRUE);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {	
			User user = loginService.login(id, password);	// 인증을 수행하고 로그인에 성공하면 User 객체를 리턴한다
			req.getSession().setAttribute("authUser", user);// User 객체를 세션의 authUser 속성에 저장한다
			res.sendRedirect(req.getContextPath()+"/index.jsp");	// 리다이렉트
			return null;
		} catch(LoginFailException e) {	// 로그인 실패한 경우
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
