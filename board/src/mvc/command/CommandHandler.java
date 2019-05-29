package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	// 모든 명령어 핸드러 클래스가 공통으로 구현해야 하는 메서드를 선언
	// 명령어 핸들러 클래스는 process() 메서드를 이용해서 알맞은 로직 코드를 구현하고 결과를 보여줄 JSP의 URI를 리턴
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
	
}
