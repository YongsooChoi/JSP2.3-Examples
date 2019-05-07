# JSP2.3-Example
JSP 2.3 예제 따라하기
설정 부분: JSP 페이지에 대한 설정 정보
	JSP 페이지가 생성하는 문서의 타입(종류)
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page … %> : page directive
	JSP 페이지에서 사용할 커스텀 태그
	JSP 페이지에서 사용할 자바 클래스 지정

생성 부분: HTML 코드 및 JSP 스크립트

JSP 페이지의 구성요소
JSP 페이지에 대한 정보를 지정
웹 브라우저가 전송한 데이터를 읽어오는 기능
JSP 페이지에서 사용할 데이터를 생성하는 실행 코드
웹 브라우저에 문서 데이터를 전송해주는 기능

JSP가 제공하는 것
-	디렉티브(Directive): JSP 페이지에 대한 설정 정보를 지정
<%@ 디렉티브이름 속성1=”값1” 속성2=”값2” … %>
<%@ page contentType="text/html; charset=UTF-8" %>
page: JSP 페이지에 대한 정보 지정. 문서의 타입, 출력 버퍼의 크기, 에러 페이지 등
taglib: JSP 페이지에서 사용할 태그 라이브러리를 지정한다.
include: JSP 페이지의 특정 영역에 다른 문서를 포함시킨다.
-	스크립트 요소: JSP 문서의 내용을 동적으로 생성하기 위해 사용, 
스크립트릿(Scriptlet): 자바 코드를 실행
표현식(Expression): 값을 출력
선언부(Declaration): 자바 메서드(함수)를 만든다.
-	표현 언어(Expression Language): 스크립트 코드보다 코드를 간결하게 한다
${ expression;값을 생성하는 코드 }
-	기본 객체(Implicit Object): 웹 앱 프로그래밍을 하는 데 필요한 기능을 제공
Ex) request, response, session, application, page 등
(요청 파라미터 읽어오기, 응답 결과 전송하기, 세션 처리하기, 웹 앱 정보 읽어오기 등)
-	정적인 데이터
-	표준 액션 태그(Action Tag): 
<jsp: 액션태그이름>
Ex) <jsp:include> : 특정 페이지의 실행 결과를 현재 위치에 포함시킬 때
-	커스텀 태그(Custom Tag)와 표준 태그 라이브러리(JSTL)
커스텀 태그: 개발자가 직접 개발하며 JSP를 확장시켜주는 기능, 코드의 중복이나 스크립트 코드의 복잡함을 없애기 위해 모듈화하거나 함
JSTL(JavaServer Pages Standard Tag Library): 커트텀 태그 중 별도로 표준화한 태그 라이브러리





request 기본객체
웹 브라우저의 파라미터 전송 방식 ( GET 메서드와 POST 메서드)
GET: 요청 URL에 파라미터(쿼리 문자열 query string)를 붙여서 전송한다. (default)
	이름1=값1&이름2=값2&…&이름n=값n			( & ampersand )
	URL의 쿼리 문자열로 전송되기 때문에, 폼을 사용하지 않아도 파라미터 전송 가능
	웹 브라우저, 웹 서버, 웹 컨테이너에 따라 전송할 수 있는 파라미터 값의 길이에 제한 있을 수 있다.

POST: 데이터 영역을 이용해서 파라미터를 전송한다.
	전송할 수 있는 파라미터의 길이에 제한이 없다.

response 기본객체 – 응답 정보에 헤더 정보를 추가, 리다이렉트 하기
웹 브라우저에 헤더 정보 전송하기
addDateHeader(String name, long date): name 헤더에 date를 추가한다.
addHeader(String name, String value): name 헤더에 value를 값으로 추가한다.
addIntHeader(String name, int value): name 헤더에 정수 값 value를 추가한다.

setDateHeader(String name, long date): name 헤더의 밧을 date로 지정한다.
setHeader(String name, String value): name 헤더의 값을 value로 지정한다.
setIntHeader(String name, int value): name 헤더의 값을 정수 값 value로 지정한다.
containsHeader(String name): 이름이 name인 헤더를 포함하고 있는지 여부를 나타낸다.

웹 브라우저 캐시 제어를 위한 응답 헤더 입력
웹 브라우저가 서버가 생성한 결과를 출력하지 않고 캐시에 저장된 데이터를 출력하는 경우는?
캐시는 동일한 데이터를 중복해서 로딩하지 않도록 할 때 사용한다. 웹 브라우저는 첫 번째 요청 시 응답 결과를 로컬 PC의 임시 보관소인 캐시에 저장하고, 이후 동일한 URL에 대한 요청이 있으면 WAS에 접근하지 않고 로컬에 저장된 응답 결과를 사용한다. (변경이 발생하지 않는 JSP의 응답 결과, 이미지, 정적 HTML 등에 유용)

HTTP는 특수한 응답 헤더를 통해서 웹 브라우저가 응답 결과를 캐시 할 것인지에 대한 여부를 설정
Cache-Control: (HTTP 1.1버전) “no-cache”, “no store”
Pragma: (HTTP 1.0버전) “no-cache”
Expires: (HTTP 1.0버전) 응답 결과 만료일 지정, 현재 시간 이전으로 설정함으로써 캐시에 보관하는 것을 막는다.

리다이렉트를 이용해서 페이지 이동 : 웹 서버가 웹 브라우저에게 다른 페이지로 이동하라고 응답하는 기능
	response.sendRedirect(String location)
	URLEncoder.encode(String value ,”charset”)

JSP 주석
스크립트릿과 선언부의 코드 블록은	//
JSP 코드 자체는 				<%-- --%>


 

JSP의 요청 처리 과정
	JSP에 해당하는 서블릿이 존재하지 않을 경우 (과정1.1)
-	JSP 페이지로부터 자바 코드를 생성한다. (과정1.2)
-	자바 코드를 컴파일해서 서블릿 클래스를 생성한다. (과정1.3)
-	서블릿에 클라이언트 요청을 전달한다. (과정2.1)
-	서블릿이 요청을 처리한 결과를 응답으로 생성한다. (과정2.2)
-	응답을 웹 브라우저에 전송한다. (과정3)

	JSP에 해당하는 서블릿이 존재하는 경우 (즉, 과정1.1~1.3을 거친 경우)
-	서블릿에 클라이언트 요청을 전달한다. (과정2.1)
-	서블릿이 요청을 처리한 결과를 응답으로 생성한다. (과정2.2)
-	응답을 웹 브라우저에 전송한다. (과정3)

JSP를 실행한다 -> JSP 페이지로부터 자바 소스 파일로 변환한 것을 컴파일 한 서블릿 클래스를 실행한다;

출력 버퍼와 응답
JSP 페이지는 출력 버퍼에 임시로 응답 결과를 저장했다가 한 번에 웹 브라우저에 전송한다.
-	데이터 전송 성능 향상 (작은 단위로 여러 번 보다 큰 단위로 한 번에 데이터를 전송)
-	JSP 실행 도중에 버퍼를 비우고 새로운 내용 전송 가능
<jsp:forward>기능과 에러 페이지 처리 기능
-	버퍼가 다 차기 전까지 헤더 변경 가능
HTTP 구조상 응답 상태 코드와 함게 헤더 정보를 가장 먼저 웹 브라우저에 전송한다. WAS는 처음 버퍼의 내용을 웹 브라우저로 전송하기 전에 헤더 정보를 전송하기 때문에, 버퍼의 내용을 웹 브라우저에 전송하기 전가지는 헤더 정보를 얼마든지 변경할 수 있다.

page 디렉티브에서 버퍼 설정하기: buffer 속성과 autoFlush 속성
JSP 페이지가 사용할 버퍼의 크기를 지정 <%@ page buffer = “4kb” %> 일반적으로 하지 않는다. JSP 규약은 이를 지정하지 않을 시 최소 8KB 이상의 크기를 갖는 버퍼를 사용하도록 규정하고 있다. 사용하고 싶지 않다면 <%@ page buffer = “none” %> 이때는 JSP 페이지 출력 내용을 곧바로 웹 브라우저에 전송한다. 

플러시(flush): 버퍼가 다 찼을 때, 전송하거나 저장하여 버퍼를 비우는 것
autoFlush 속성 (버퍼가 다 찼을 때)	true: 버퍼를 플러시하고 계속해서 작업을 진행한다.
					false: 예외(exception)를 발생시키고 작업을 중지한다.
웹 어플리케이션 폴더 구성과 URL 매핑
webapps\chap04\WEB-INF		: 웹 어플리케이션 설정 정보를 담고 있는 web.xml 파일이 위치한다.
webapps\chap04\WEB-INF\classes	: 웹 어플리케이션에서 사용하는 클래스 파일이 위치한다.
webapps\chap04\WEB-INF\lib		: 웹 어플리케이션에서 사용하는 jar 파일이 위치한다.

서블릿 2.5 / JSP 2.1 이후 버전, web.xml 파일을 작성해야 하는 경우
-	서블릿을 직접 설정하는 경우
-	리스너를 직접 설정하는 경우
-	특정 URL에 속하는 JSP들에 대해 공통 속성값을 설정하는 경우

request.getContextPath(): 컨텍스트 경로를 제공하는 메서드 ex) chap04, ROOT 등

웹 애플리케이션 폴더 내에 하위 폴더 사용: JSP 페이지를 기능별로 분류 (개발 과정, 유지보수에 유리)

웹 어플리케이션을 WAS에 배포하는 방법
-	대상 폴더에 파일을 직접 복사 (서버의 특정 폴더에 파일을 직접 저장, 복사, FTP 파일 전송 기능 이용)
-	war 파일(Web Application Archive: 웹 앱 구성 요소를 하나로 묶어 놓은 파일)로 묶어서 배포

war 파일로 묶으려면 JDK의 jar 명령어를 사용하면 된다. jar 명령어는 javac 명령어와 동일하게 [JDK설치폴더]\bin 폴더에 포함되어 있다. PATH 환경 변수에 [JDK설치폴더]\bin 폴더를 추가해주면 전체 경로를 입력할 필요 없이 jar 명령어를 실행할 수 있다.

cvf 옵션을 사용해서 jar 명령어를 실행하면 war 파일을 생성할 수 있다.
C:\apache-tomcat-9.0.17\webapps\chap04>jar cvf chap04.war *
( c: 새로운 파일을 생성, v: 세부 정보를 콘솔(명령 프롬프트)에 표시, f: 생성할 파일의 이름을 지정,
*: 현재 폴더의 모든 파일과 하위 폴더 대상)
-> chap04.war 파일을 [톰캣]\webapps 폴더에 복사해주면 배포가 된다. (톰캣 실행 시, chap04 폴더가 생성되고, 이 폴더는 \chap04 컨텍스트 경로를 갖는다.)

JSP의 9가지 기본 객체
request 		- 클라이언트의 요청 정보를 저장한다.		javax.servlet.http.HttpServletRequest
response	- 응답정보를 저장한다. 				javax.servlet.http.HttpServletResponse
session 		- HTTP 세션 정보를 저장한다. (10장, 세션)		javax.servlet.http.HttpSession
out 		- JSP 페이지가 생성하는 결과를 출력할 때 사용하는 출력 스트림. 	javax.servlet.ServletContext
pageContext 	- JSP 페이지에 대한 정보를 저장한다. 		javax.servlet.jsp.PageContext
application 	- 웹 앱에 대한 정보를 저장한다. 			javax.servlet.ServletContext
exception 	- 에러 페이지에서만 사용한다. (6장, 에러처리)	javax.lang.Throwable
page 		- JSP 페이지를 구현한 자바 클래스 인스턴스이다. 	javax.lang.Object
config 		- JSP 페이지에 대한 설정 정보를 저장한다. 	javax.servlet.ServletConfig

out 기본 객체 : 	
JSP 페이지가 생성하는 모든 내용은 out 기본 객체를 통해 전송된다. (브라우저에 테이터를 전송하는 출력 스트림)
복잡한 조건 비교 때문에 출력 코드가 복잡해지는 경우가 아니면 out 기본 객체를 사용하지 않는 것이 좋다.
출력 가능한 자료형 : boolean, char, char[], double, float, int, long, 그리고 Sring
JSP 페이지가 사용하는 버퍼는 out 기본 객체가 내부적으로 사용하는 버퍼이다. <%@ page buffer=”16kb” %>
(예제: chap05/useOutObject.jsp, bufferInfo.jsp)

pageContext 기본 객체 : JSP 페이지와 일대일로 연결된 객체
기본 객체 구하기, 속성 처리하기, 에러 데이터 구하기(6장), 페이지의 흐림 제어하기(7장)
직접 사용하는 경우는 드물고, 커스텀 태그를 구현할 때 사용한다.
(예제: chap05/usePageContext.jsp, )

application 기본 객체 : 웹 어플리케이션과 관련된 기본 객체
특정 웹 앱에 포함된 모든 JSP 페이지는 하나의 application 기본 객체를 공유하게 된다.
초기 설정 정보를 읽어오거나, 서버 정보, 웹 앱이 제공하는 자원(파일)을 읽어올 수 있다.

웹 앱 초기화 파라미터 읽어오기 
초기화 파라미터 WEB-INF/web.xml 파일에 <context-param> 태그를 사용하여 추가

web.xml 파일 : 웹 앱을 위한 설정 정보를 담고 있는 파일. 꼭 필요하지 않기 때문에 필요할 때만 작성
(톰캣은 web.xml 파일을 변경하면 웹 앱을 자동으로 다시 시작하는 웹 컨테이너이다. 아닌 경우 수동 재시작.)

웹 앱 초기화 피라미터는 언제 사용할까? : 이름처럼 웹 앱을 초기화하는 데 필요한 설정 정보를 지정하기 위해 사용 된다. 예로, DB 연결과 관련된 설정 파일의 경로나, 로깅 설정 파일, 웹 앱 주요 속성 정보를 담고 있는 파일의 경로 등을 지정할 때 사용한다.
(C:\apache-tomcat-9.0.17\webapps\chap05\WEB-INF\web.xml, chap05/readInitParameter.jsp)

서버 정보 읽어오기 : 현재 사용중인 웹 컨테이너에 대한 정보를 읽어오는 메서드 제공
(http://localhost:8080/chap05/viewServerInfo.jsp)

로그 메시지 기록하기 : 웹 컨테이너가 사용하는 로그 파일에 고르 메시지를 기록하는 메서드 제공
(chap05/useApplicationLog.jsp, chap05/useJSPLog.jsp)

웹 앱의 자원 구하기 : 웹 앱 폴더에 위치한 파일 사용, 폴더 내 경로 이용하는 방법 알기

웹 어플리케이션의 네 가지 영역(scope) 구성
PAGE 영역: 하나의 JSP 페이지를 처리할 때 사용되는 영역 – pageContext 기본 객체
REQUEST 영역: 하나의 HTTP 요청을 처리할 때 사용되는 영역 (하나의 요청을 처리하는 데 사용되는 모든 JSP 페이지를 포함) – request 기본 객체
SESSION 영역: 하나의 웹 브라우저와 관련된 영역(세션이 생성되고 하나의 웹 브라우저와 관련된 모든 요청 포함) – session 기본 객체
APPLICATION 영역: 하나의 웹 어플리케이션과 관련된 전체 영역 (위의 것을 모두 포함) – application 기본 객체











웹 브라우저에서 한 번의 요청은 하나의 request 기본 객체와 관련된다. 웹 즈라우저가 결과를 받으면 그 요청과 관련된 request 기본 객체는 사라진다. 즉, 웹 브라우저가 요청할 때마다 새로운 request 기본 개체가 생성된다. 즉, 매번 새로운 REQUEST 영역이 생성된다.
하나의 요청을 처리하는 데 두 개 이상의 JSP가 사용될 수도 있다. 예를 들어, 웹 브라우저가 호출한 JSP 페이지가 다른 JSP를 include 하거나 forward 할 수 있는데, 이 경우 두 JSP 페이지는 같은 요청 범위에 속하게 된다. 즉. 같은 request 기본 객체를 공유하게 된다. (7장) 하나의 웹 브라우저는 하나의 세션과 관련된다. 서로 다른 두 개의 웹 브라우저가 같은 JSP 페이지를 사용한다 하더라도 두 웹 브라우저는 서로 다른 SESSION 영역에 포함되며, 서로 다른 session 기본 객체를 사용하게 된다.
모든 JSP는 한 개의 application 기본 객체를 공유하며, application 기본 객체는 APPLICATION 영역에 포함된다.

JSP 기본 객체의 속성(Attribute) 사용하기 : JSP 페이지 사이에서 정보를 주고받거나 공유하기 위한 목적으로 각 기본 객체가 존재하는 동안 기본 객체의 속성을 사용한다. <속성이름, 값>의 형태.
(chap05/setApplicationAttribute.jsp)
속성의 값 타입: 속성의 이름은 String 타입이지만, 값은 모든 클래스 타입이 올 수 있다. 오토 박싱/언박싱 가능
속성의 활용 용도
pageContext: (한 번의 요청을 처리하는) 하나의 JSP 페이지 내에서 공유할 RKQT을 저장한다. 주로 커스텀 태그에서 새로운 변수를 추가할 때 사용한다. (PAGE 영역)
request: 한 번의 요청을 처리하는 데 사용되는 모든 JSP 페이지에서 공유할 값을 저장한다. 주로 하나의 요청을 처리하는 데 사용하는 JSP 페이지 사이에서 정보를 전달하기 위해 사용된다. (REQUEST 영역)
session: 한 사용자와 관련된 정보를 JSP 사이에 공유하기 위해 사용한다. 사용자의 로그인 정보와 같은 것들을 저장한다. (SESSION 영역)
application: 모든 사용자를 위해 공유할 정보를 저장한다. 임시 폴더 경로와 같이 웹 앱의 설정 정보를 주로 저장한다. (APPLICATION 영역)

에러 처리, 예외
주요 응답 상태 코드: HTTP 프로토콜은 응답 상태 코드를 이용해서 서버의 처리 결과를 웹 브라우저에 알려준다.
200: 요청을 정상적으로 처리함
307: 임시로 페이지를 리다이렉트함 (response.sendRedirect()를 이용할 경우)
400: 클라이언트의 요청이 잘못된 구문으로 구성됨
401: 접근을 허용하지 않음
404: 요청한 URL을 처리하기 위한 자원이 존재하지 않음
405: 요청한 메서드(GET, POST, HEAD 등의 전송 방식)를 허용하지 않음
500: 서버 내부 에러가 발생함(예를 들어, JSP에서 예외가 발생함)
503: 서버가 일시적으로 서비스를 제공할 수 없음(급격하게 부하가 몰리거나 서버가 임시 보수 중인 경우가 해당)
http://goo.gl/D9th8N 참조

에러 페이지의 우선순위와 지정 형태
1.	page 디렉티브의 errorPage 속성에 지정한 에러 페이지를 보여준다.
2.	JSP 페이지에서 발생한 익셉션 타입이 web.xml 파일의 <exception-type>에 지정한 익셉션 타입과 동일한 경우 지정한 에러 페이지를 보여준다.
3.	에러 코드가 web.xml 파일의 <error-code>에 지정한 에러 코드와 동일한 경우 지정한 에러 페이지를 보여준다.
4.	아무것도 해당하지 않는 경우 웹 컨테이너가 제공하는 기본 에러 페이지를 보여준다.
우선순위를 고려해 다음과 같이 에러 페이지를 지정한다 : 전용 에러 페이지가 필요한 경우 page 디렉티브의 errorPage 속성을 사용한다. 범용적인 에러 코드에 대해 web.xml에 <error-code> 태그를 추가해 에러 페이지를 지정하고, 별도로 처리해야 하는 예외에 대해서는 <exception-type> 태그를 추가해서 에러 페이지를 지정한다.

CHAPTER 07 페이지 모듈화와 요청 흐름 제어
<jsp:include> 액션 태그를 이용한 공통 영역 작성 (동일한 상단, 좌측 메뉴, footer 등) – 코드 중복 및 수정
<jsp:include page=”포함할 페이지” flush=”true /> 화면의 구성 요소에 대한 모듈로 사용할 수 있다.

<jsp:param>으로 포함할 페이지에 파라미터 추가하기 (<jsp:include>의 자식 태그로 추가)
<% String type = “typeA”; %>
<jsp:include page=”/module/top.jsp” flush=”false”’>
    <jsp:param name=”name” value=”최범균” />		<%-- value에 값을 직접 입력 -->
    <jsp:param name=”type” value=”<% type %>” /> 	<%-- 표현식으로 값 입력 -->
</jsp:include>
<jsp:param> 액션 태그로 추가한 파라미터는 <jsp:include> 액션 태그로 포함하는 페이지에서만 유효하다. 기존 파라미터 값을 유지한 채 새 파라미터 값을 추가하며, 이때 새롭게 추가된 값이 우선한다.
(http://localhost:8080/chap07/body_main.jsp?name=cbk 과 body_sub.jsp 참조)

<%
	request.setCharacterEncoding("utf-8");	// <jsp:param>으로 전달되는 값은 request.setCharacterEncoding()에 명시한 캐릭터셋으로 인코딩해서 전달한다. 알맞은 캐릭터셋을 지정하지 않으면 설정한 값이 올바르게 전달되지 않는다.
%>

include 디렉티브를 이용한 중복된 코드 삽입 <%@ include file=”포함할 파일의 경로” %>
<jsp:include>는 다른 JSP로 실행 흐름을 이동시켜 실행 결과를 현재 위치에 포함하는 방식이고, include 디렉티브는 다른 파일의 내용을 현재 위치에 삽입한 후에 JSP 파일을 자바 파일로 변환하고, 서블릿 클래스로 컴파일하는 방식
활용: 모든 JSP 페이지에서 사용하는 변수 지정, 저작권 표시와 같이 모든 페이지에서 중복되는 간단한 문장

.jspf 코드 조각 자동 포함 기능
<jsp-property-group> : JSP의 프로퍼티를 포함한다.
<url-pattern> : 프로퍼티를 적용할 JSP 파일의 URL 패턴을 지정한다.
<include-prelude> : url-pattern 태그에 지정한 패턴에 해당하는 JSP 파일의 앞에 삽입할 파일을 지정한다.
<include-coda> : url-pattern 태그에 지정한 패턴에 해당하는 JSP 파일의 뒤에 삽입할 파일을 지정한다.

두 개 이상 <jsp-property-group> 태그 설정한 경우, 차례대로 적용
1.	<url-pattern>/view/*</url-pattern>
2.	<url-pattern>*.jsp</url-pattern>

비교 항목	<jsp:include>	Include 디렉티브
처리 시간	요청 시간에 처리	JSP 파일을 자바 소스로 변환할 때 처리
기능	별도의 파일로 요청 처리 흐름을 이동	현재 파일에 삽입시킴
데이터 전달 방법	Request 기본 객체나 <jsp:param>을 이용한 파라미터 전달	페이지 내의 변수를 선언한 후, 변수에 값 저장
용도	화면의 레이아웃의 일부분을 모듈화할 때 주로 사용	다수의 JSP 페이지에서 공통으로 사용되는 변수를 지정하는 코드나 저작권 같은 문장을 포함

<jsp:forward page=”이동할 페이지” /> 액션 태그를 이용한 다른 JSP 페이지로 요청 처리를 전달
-	from.jsp가 아닌 to.jsp가 생성한 응답 결과를 웹 브라우저에 전달한다. (간결하고 구조적인 프로그래밍)
-	from.jsp에서 사용한 request, response 기본 객체를 to.jsp에 그대로 전달한다.
-	웹 브라우저의 주소는 from.jsp 그대로이다. 즉, 리다이렉트처럼 to.jsp로 변경되지 않는다.
<jsp:forward>액션 태그의 활용 : 조건에 따라 다른 페이지로 이동하여 그 결과를 보여줄 때. <jsp:param> 가능

page 속성 경로의 절대 경로와 상대 경로

request 기본 객체의 속성을 이용한 값 전달 방식(타입 변환 과정 불필요하기 때문에 <jsp:forward>보다 편리)은 웹 앱 개발에서 중요한 기법 중 하나이다. 특히 이 방식은 MVC 패턴이라는 것에 기반하여 웹 앱을 구현할 때 필수 요소이기 때문에 request 기본 객체의 속성을 사용하는 방법에 대해서 반드시 이해해야 한다.
