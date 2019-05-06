<%@ page contentType="text/html; charset=utf-8" %>
<%!
	public int muliply(int a, int b) {
		int c = a * b;
		return c;
	}
%>>
<html>
<head>
<title>선언부를 사용한 두 정수값의 곱</title>
</head>
<body>

10 * 25 = <%= multiply(10, 25) %>	<%-- 메서드를 호출(call) --%>

</body>
</html>