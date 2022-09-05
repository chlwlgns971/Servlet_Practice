<%@page import="kr.or.ddit.vo.FormDataVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	FormDataVO vo = (FormDataVO)request.getAttribute("vo");
%>
<table border="1">
	<tr>
		<th>ParamIpt1</th>
		<td><%=vo.getParamIpt1() %></td>
	</tr>
	<tr>
		<th>ParamIpt2</th>
		<td><%=vo.getParamIpt2() %></td>
	</tr>
	<tr>
		<th>ParamIpt3</th>
		<td><%=vo.getParamIpt3() %></td>
	</tr>
	<tr>
		<th>ParamIpt4</th>
		<td><%=vo.getParamIpt4() %></td>
	</tr>
	<tr>
		<th>ParamIpt5</th>
		<td><%=vo.getParamIpt5() %></td>
	</tr>
	<tr>
		<th>ParamIpt6</th>
		<td><%=vo.getParamIpt6() %></td>
	</tr>
	<tr>
		<th>ParamIpt7</th>
		<td><%=vo.getParamIpt7() %></td>
	</tr>
	<tr>
		<th>ParamIpt8</th>
		<td><%=vo.getParamIpt8() %></td>
	</tr>
</table>
</body>
</html>