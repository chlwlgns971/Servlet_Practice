<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
	public static String temp = "TEMP"; //jsp에서 전역변수는 다른 jsp에서 사용이 불가능하다. class를 식별하는것은 톰캣의 책임이기 때문	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
	<h4>흐름 제어(페이지 이동 구조)</h4>
	<pre>
		1. Request Dispatch : 이동과정에서 원본 요청을 가지고 이동하는 방식(forward). =>요청이 남이있음
				=> 1) Forward : 요청 -> A 페이지 -> B 페이지 -> 응답 (Model12 Architecture에서 주로 사용)
				   2) Include : 요청 -> A 페이지 -> B 페이지 -> A 페이지로 복귀(B 데이터 일부) -> 응답 (페이지 모듈화에서 주로 사용)
				   <%
				   //Forward 이동코드
				   String path = "/02/standard.jsp";
				   request.getRequestDispatcher(path).forward(request, response);
				   //Include 이동코드
				   //request.getRequestDispatcher(path).include(request, response); //standard와 flowControl jsp가 한페이지에 같이 구현
				   %>
		2. Redirect : 이동 과정에서 최초의 요청에 대한 응답이 먼저 전송됨. =>요청이 남아있지 않음
				최초의 요청(명령)에 대한 처리가 완료되어 더이상 저장할 필요가 없음
				=> 요청1 -> 응답1(Line(302), Header(Location)) -> 클라이언트 -> 요청2(Location) -> 응답2(Body contents)
				<%
					//Redirect 이동코드
					//response.sendRedirect(request.getContextPath()+path);
				%>
	</pre>
</body>
</html>