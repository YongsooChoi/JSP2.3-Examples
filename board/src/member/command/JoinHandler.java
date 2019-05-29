/*
 * Get 방식 요청 -> 폼을 보여주는 뷰인 joinForm.jsp를 리턴
 * POST 방식 요청 -> 회원가입을 처리하고 결과를 보여주는 뷰인  joinSuccess.jsp를 리턴
 */
package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {	// GET 또는 POST가 아니면 405 응답코드를 전송
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	// 폼을 보여주는 뷰 경로를 리턴한다
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	// 폼 전송을 처리한다
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// 폼에 입력한 데이터를 이용해서, JoinRequest 객체를 생성한다
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		
		// 에러 정보를 담을 맵 객체를 생성하고, "errors" 속성에 저장한다
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);	// errors 맵 객체를 request의 "errors" 속성에 저장한다
		
		joinReq.validate(errors);	// JoinRequest 객체의 값이 올바른지 검사한다
		
		if(!errors.isEmpty()) {	// errors 맵 객체에 데이터가 존재하면, 폼 뷰 경로를 리턴한다
			return FORM_VIEW;
		}
		
		try {
			joinService.join(joinReq);				// JoinService의 join() 메서드를 실행하고
			return "WEB-INF/view/joinSuccess.jsp";	// 가입 처리에 성공하면 성공 결과를 보여줄 뷰 경로를 리턴한다
		} catch(DuplicateIdException e) {
			errors.put("duplicatedId", Boolean.TRUE);	// 동일한 아이디로 가입한 회원이 존재하면 errors에 키 추가
			return FORM_VIEW;							// 폼을 위한 뷰 경로를 리턴한다
		}
	}

}
