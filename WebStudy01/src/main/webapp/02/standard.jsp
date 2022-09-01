<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
<h4>JSP(JavaServerPage) Spec</h4>
<%=new Date() %>
<%
	out.print(new Date());
%>
<pre>
	: 웹상에서 랜더링할 웹 페이지를 생성하기 위한 스펙. script, 토큰의 종류가 여러가지.
	표준 JSP 구성 요소
	1. 정적 텍스트
		HTML, CSS, JS, text
	2. 스크립틀릿 (standard_jsp.java 참고. 웹 베이스의 JSP파일의 URL로 최초의 요청이 들어올 때마다 톰캣이 새로 만든다. Mapping이 필요 없다.)
		1) directive(지시자) : &lt;%@ %&gt;
			page : JSP 페이지에 대한 기본 설정 정보. 꼭!! 필요하다.
			include : 정적 내포.
			taglib : 커스텀 태그 로딩.
		2) scriptlet : <% // java code %>
			<%
				// 지역 코드 _JSPService
				String temp = "TEMP"; // temp는 메소드 (_jspService)안의 지역 변수이다.
				// return;
			%>
		3) expression(표현식) : &lt;= 출력할 값이나 연산식. &gt;
		4) declaration(선언부) : 
		<%! 
			// 전역 코드 standard_jsp
			private void test(){} // 메소드 선언 가능하다. -> 메소드 안에 메소드 선언은 불가능하다. -> 전역 지역이다. -> 클래스 안쪽이다.
			private String txt; 
			// txt 변수는 standard_jsp 서블릿 안의 전역 변수이다.
		%>
		5) comment(주석) : <%-- --%>
			- client side comment : HTML, CSS, JS -> 응답 데이터에 포함된다.
			<!-- HTML Comment -->
			- server side comment : JAVA, JSP -> 응답 데이터에 포함되지 않는다. : 응답 데이터의 사이즈를 줄일 수 있다. 클라이언트에게 보이지 않기 때문에 보안에 좋다.
			<%-- JSP Comment --%>		
		3. 기본 객체
		4. action tag 
		5. EL(Expression Language, 표현 언어)
		6. JSTL(JspStandardTagLibrary)
</pre>
</body>
</html>