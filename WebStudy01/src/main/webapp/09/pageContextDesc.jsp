<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/pageContextDesc.jsp</title>
</head>
<body>
	<h4>pageContext(PageContext)</h4>
	<pre>
		: 가장 먼저 생성되는 기본 객체.
		: 나머지 기본 객체 참조 소유.
		
		1. 흐름제어(flow Control, request dispatch)
		<%--
			pageContext.include("/08/sessionTimer.jsp");
			//request.getRequestDispatcher("/08/sessionTimer.jsp").include(request, response);
		--%>
		
		2. 에러 데이터 확보(에러 처리 페이지)
		<%--
			if(1==1)
				throw new ArithmeticException("강제 0으로 나눴음. 예외발생!!!");
		--%> 
		
		3. 속성 데이터 관리
		<%
			//request.setAttribute(name, Object); //응답을 보내면 데이터가 사라짐(무거워질 일이 없음) (request scope)
			//session.setAttribute(name, value); //(session scope)
			//application.setAttribute(name, Object); // -> 안 없어진다.(서버는 무거워짐) (application scope)
			//pageContext.setAttribute(name, value) (page scope)
			//pageContext.setAttribute(name, value, scope);
			// 생존범위가 좁은 순서 : pageContext -> request -> session -> application
			
			//pageContext.setAttribute(name, value, pageContext.PAGE_SCOPE);
		%>
		***scope : 해당 저장 영역을 관리하는 기본 객체와 생명주기가 동일한 저장공간.
		attribute : scope를 통해 공유되는 데이터.
			1. page scope
			2. request scope
			3. session scope
			4. application scope
			setAttribute(name, vlaue), getAttribute(name), removeAttribute(name)
		==================================
		<%
			pageContext.setAttribute("pageAttr", "페이지 속성");
			request.setAttribute("requestAttr", "요청 속성");
			session.setAttribute("sessionAttr", "세션 속성");
			application.setAttribute("applicationAttr", "어플리케이션 속성");
			
			//pageContext.include("/09/attributeView.jsp");
			response.sendRedirect(request.getContextPath()+"/09/attributeView.jsp");
		%>
		<a href="attributeView.jsp">어트리뷰트 뷰로 이동</a>
	</pre>
</body>
</html>