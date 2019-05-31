package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import article.service.WriteArticleService;
import article.service.WriteRequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/newArticleForm.jsp";
	private WriteArticleService writeService = new WriteArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
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
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");	// 로그인한 사용자 정보를 구한다.
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);	// 생성한 WriteRequest 객체가 유효한지 검사한다.
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newArticleNo = writeService.write(writeReq); // WriteArticleService를 이용해서 게시글을 등록하고, 등록된 게시글의 ID를 리턴받는다.
		req.setAttribute("newArticleNo", newArticleNo);	// 위 ID를 속성에 저장한다. 처리 결과를 보여줄 JSP는 이 속성값을 사용해서 링크를 생성한다.
		
		return "/WEB-INF/view/newArticleSuccess.jsp";
	}
	
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getId(), user.getName()),
						req.getParameter("title"),
						req.getParameter("content"));
	}
}
