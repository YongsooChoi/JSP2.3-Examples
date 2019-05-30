package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/changePwdForm.jsp";
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);	// 폼을 위한 뷰 경로를 리턴
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);	// 폼 전송을 처리
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// LoginCheckFilter를 이용해서 로그인한 사용자만 암호 변경 기능을 실생할 수 있도록 하므로 세션에서 구한 사용자 정보는 null이 아니다.
		User user = (User)req.getSession().getAttribute("authUser");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		String curPwd = req.getParameter("curPwd");
		String newPwd = req.getParameter("newPwd");
		
		if(curPwd == null || curPwd.isEmpty()) {
			errors.put("curPwd", Boolean.TRUE);
		}
		if(newPwd == null || newPwd.isEmpty()) {
			errors.put("newPwd", Boolean.TRUE);
		}
		if(!errors.isEmpty() ) {
			return FORM_VIEW;
		}
		
		try {
			changePwdSvc.changePassword(user.getId(), curPwd, newPwd);	// 암호 변경 기능을 실행,
			return "/WEB-INF/view/changePwdSuccess.jsp";				// 성공시 뷰 리턴
		} catch(InvalidPasswordException e) {							// 현재 암호가 올바르지 않은 경우,
			errors.put("badCurPwd", Boolean.TRUE);						// 관련 에러 코드 추가
			return FORM_VIEW;											// 폼 뷰 경로 리턴
		} catch(MemberNotFoundException e) {							// 암호를 변경할 회원 아이디 존재하지 않는 경우,
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);			// 잘못된 요청을 의미하는 400 상태코드
			return null;
		}
	}
}
