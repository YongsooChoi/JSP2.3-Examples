package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingFile extends HttpServlet {
	
	// <커맨드, 핸들러인스턴스> 매핑 정보 저장
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	public void init() throws ServletException {
		
		String configFile = getInitParameter("configFile"); 					// 초기화 파라미터의 값을 읽어온다
		String configFilePath = getServletContext().getRealPath(configFile); 	// 설정 파일 경로 구한다
		Properties prop = new Properties(); 				// Properties는 (이름, 값) 목록을 갖는 클래스- (커맨드 이름, 클래스 이름)
		
		try (FileInputStream fis = new FileReader(configFilePath)) { 
			prop.load(fis);												// 설정파일로부터 매핑 정보를 읽어와 Properties 객체에 저장
		} catch(IOException e) {
			throw new ServletException(e);
		}
		
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next(); 			// 프로퍼티 이름을 커맨드 이름으로 사용한다.	
			String handlerClassName = prop.getProperty(command); // 커맨드 이름에 해당하는 핸들러 클래스 이름을 Properties에서 구한다.
			try {
				Class<?> handlerClass = Class.forName(handlerClassName); // 핸들러 클래스 이름을 이용해서 Class 객체를 구한다.
				CommandHandler handlerInstance = (CommandHandler)handlerClass.newInstance(); // Class로부터 핸들러 객체를 생성한다.
				commandHandlerMap.put(command, handlerInstance); // commandHandlerMap에 (커맨드, 핸들러 객체) 매핑 정보를 저장한다. 
			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("cmd");	// 클라이언트가 요청한 명령어를 구한다. cmd 파라미터를 명령어로 사용한다.
		CommandHandler handler = commandHandlerMap.get(command); // 요청을 처리할 핸들러 객체를 구한다
		if(handler == null) {
			handler = new NullHandler();
		}
		String viewPage = null;
		try {
			viewPage = handler.process(request, response); 	// 구한 핸들러 객체의 process() 메서드를 호출해서 요청을 처리
		} catch(Exception e) {								// 결과로 보여줄 뷰 페이지 경로를 리턴 값으로 받는다
			throw new ServletException(e);
		}
		if(viewPage != null) {	// viewPage가 null이 아닐 경우, 핸들러 인스턴스가 리턴한 뷰 페이지로 이동한다.
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
