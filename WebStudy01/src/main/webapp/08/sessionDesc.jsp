<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/sessionDesc.jsp</title>
</head>
<body>
	<h4>session (HttpSession)</h4>
	<pre>
		곁다리로 Cookie까지.
		Http : Connectless, Stateless
		session(시간) : stateless 단점을 보완하기 위해 서버쪽에 저장된 상태 정보.
						하나의 어플리케이션을 사용하기 시작한 순간부터 사용 종료까지의 기간.
		생성 : 식별 대상이 되는 사용자가 최초의 요청을 전송, 세션의 ID가 식별성을 갖도록 생성됨.
			session id = <%=session.getId() %>
			session 생성시점 : <%=new Date(session.getCreationTime()) %>
			session timeout : <%=session.getMaxInactiveInterval() %>
			<%
				session.setMaxInactiveInterval(60*2);
			%>
			session timeout : <%=session.getMaxInactiveInterval() %>
		유지 : 세션의 식별성, tracking mode(두 피어가 세션 아이디를 공유하는 방식.)
			1) COOKIE : Request Header을 통해 세션 아이디 재전송(공유)
			2) URL <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">쿠키없이 세션 유지</a>
				: 세션 파라미터(Request Line)로 세션 아이디 재전송(공유), 보안 취약성.
			3) SSL(Secure Socket Layer) : 암호화된 아이디 재전송(공유)
		소멸 
			1) 로그아웃.
			
			-->timeout 이내에 새로운 요청이 발생하지 않을 때.
			2) 세션 쿠키 삭제.
			3) 브라우저 종료
	</pre>
</body>
</html>