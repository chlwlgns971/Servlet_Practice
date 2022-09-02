<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourceidentify.jsp</title>
</head>
<body>
<h4>자원의 종류의 식별 방법</h4>
<pre>
	자원의 위치와 접근하기 위한 경로 표기 방법에 따른 분류
	1. File System Resource : 파일시스템의 특정 드라이브 아래. 파일시스템 절대 경로 형태(드라이브 루트부터 시작)
		D:\contents\images\cat1.jpg
		
	2. Class path Resource : classpath 아래. classpath이후의 qualified name 형태 접근. 
		\kr\or\ddit\images\cat2.png
		<%
			Date today = new Date();
			//ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			ClassLoader classLoader = DescriptionServlet.class.getClassLoader();
			//URL cpResource = classLoader.getResource("kr/or/ddit/images/cat2.png");
			URL cpResource = DescriptionServlet.class.getResource("../images/cat2.png");
			String cpPath = cpResource.toURI().getPath();
		%>
		--> <%=cpPath %>
		
	3. Web Resource : web, URL 형태 접근.
		https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
		
		\WebStudy01\resources\images\.cat3.png
		<%
			URL webResource = application.getResource("/resources/images/cat3.png");
			
		%>
		--> <%=webResource %>
		
	*** 웹상의 자원을 식별하기 위한 주소 체계
	URI : Unified Resource Identifier 
	URL : Unified Resource Locator
	URN : Unified Resource Naming
	URC : Unified Resource Contents
	
	URL
	절대경로 -> protocol://IP[domain]:port/contextName/depth.../resourceName
	
	client side	: <%=request.getContextPath()%>>/resources/images/cat3.png
	server side : context path 이후의 경로 /resources/images/cat3.png
</pre>
<img src="/resources/images/cat3.png"/>
<img src="/WebStudy01/resources/images/cat3.png"/>
<img src="//localhost/WebStudy01/resources/images/cat3.png"/>
<img src="/WebStudy01/resources/images/cat3.png"/>

<img src="../resources/images/cat3.png"/>

</body>
</html>