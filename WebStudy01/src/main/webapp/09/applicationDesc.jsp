<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/applicationDesc.jsp</title>
</head>
<body>
	<h4>application(ServletContext)</h4>
	CAC(Context Aware Computing)
	<pre>
   application hashcode :    <%=application.hashCode()%>
   <a href="../08/sessionTimer.jsp">세션타이머</a>
   <a href="<%=application.getContextPath()%>/desc">desc servlet</a>
   1. 서버의 정보를 가져올때.
      - 서버를 식별할 때.    <%=application.getServerInfo()%>
                     <%=application.getMajorVersion()%>.<%=application.getMajorVersion()%>
      - Mime Type 확인할 때. getMimeType(file_name)
   2. Context 정보를 가져올 때.
      - 초기화 파라미터 획득. getInitParameter (web.xml -> context-param)
                     <%=application.getInitParameter("imageFolderPath")%>
      - 현재 context의 web resource 확보
      <%
      		String url = "/resources/images/cat3.png";
      		String saveUrl = "/09/cat3.png";		
      
       		String path = application.getRealPath(url);
       		String savePath = application.getRealPath(saveUrl);
       		
      		File file = new File(path);
      		File saveFile = new File(savePath);
      		
      		try(
      		FileInputStream fis = new FileInputStream(file);
      		InputStream is = application.getResourceAsStream(url);
      		BufferedInputStream bis = new BufferedInputStream(fis);
      		
      		FileOutputStream fos = new FileOutputStream(savePath);
      		BufferedOutputStream bos = new BufferedOutputStream(fos);
      		){
      			int temp = -1;
      			while ((temp = bis.read()) != -1){
                    bos.write(temp);
                 }
      		}
      %>
      url : <%=url%>
      path : <%=path%>
      file : <%=file.getPath()%>
      
      3. 로그 기록 <% application.log("메세지 기록"); %>
   
</pre>
	<img src="<%=request.getContextPath()%>/09/cat3.png">
</body>
</html>
