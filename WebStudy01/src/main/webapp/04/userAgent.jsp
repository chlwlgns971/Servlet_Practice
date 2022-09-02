<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/userAgent</title>
</head>
<body>
<h4>UserAgent</h4>
<%
String userAgent=request.getHeader("User-Agent");
String agent = userAgent.toUpperCase();
String browser="";

String browserName = BrowserType.searchBrowserName(request);
out.println(String.format("당신의 브라우저는 '%s'입니다.",browserName));

/* Map<String,String> agentMap = new LinkedHashMap<>();
agentMap.put("TRIDENT","IE");
agentMap.put("EDG","엣지");
agentMap.put("WHALE","웨일");
agentMap.put("CHROME","크롬");
agentMap.put("SAFARI","사파리");
agentMap.put("FIREFOX","파이어폭스");
agentMap.put("OTHER","기타");

browser = agentMap.get("OTHER");
for(Entry<String,String> entry : agentMap.entrySet()){
	if(agent.indexOf(entry.getKey())>-1){
		browser = entry.getValue();
		break;
	}
}
out.println(String.format("당신의 브라우저는 '%s'입니다.",browser));*/

/* if(userAgent.indexOf("Chrome")!=-1 && userAgent.indexOf("Whale")==-1) browser="당신의 브라우저는 Chrome입니다.";
else if(userAgent.indexOf("Safari")!=-1 && userAgent.indexOf("Chrome")==-1) browser="당신의 브라우저는 Safari입니다.";
else if(userAgent.indexOf("edg")!=-1) browser="당신의 브라우저는 edge입니다.";
else if(userAgent.indexOf("trident")!=-1) browser="당신의 브라우저는 익스플로어입니다.";
else if(userAgent.indexOf("Whale")!=-1) browser="당신의 브라우저는 Whale입니다.";
else browser="당신의 브라우저는 기타입니다."; */
%>
<p><%=browser %></p>
</body>
</html>