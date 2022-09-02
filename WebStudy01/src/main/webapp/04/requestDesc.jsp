<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/requestDesc.jsp</title>
</head>
<body>
<h4>request : HttpServletRequest</h4>
<pre>
	Http에 의해 패키징되는 요청의 구조.
	
	1. Request Line : URL HttpMethod(Request Method) Protocol/version
		Http Method : 요청의 목적(동사, 행위)이면서 동시에 패키징 구조 표현.
		GET(R) : 기본 METHOD
		POST(C)
		PUT(U)
		DELETE(D)
		OPTIONS : preFlight 요청으로 본 요청의 method 지원여부를 확인.
		HEADER
		TRACE
	2. Request Header : Metadata(name/value - String)
	3. Request Body(Content[Message] Body, only POST) : 전송 데이터 자체 영역.
		Parameter : 문자열로 전송
		Part : 2진 데이터로 전송(multipart -> 동시에 여러 형태의 데이터 전송)
</pre>
<form method="post" enctype="multipart/form-data">
	<input type="text" name="param" />
	<input type="file" name="filePart" />
	<input type="submit" value="전송" />
</form>

<h4>요청 헤더들</h4>
<table>
	<thead>
		<tr>
			<th>헤더이름</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
		<%	
		//StringBuffer trTag = new StringBuffer();
		//String pattern - "<tr><td></td><td></td></tr>";
		//collection view
		Enumeration<String> en = request.getHeaderNames();
		
		while(en.hasMoreElements()){
			String headerName = en.nextElement();
			String headerValue = request.getHeader(headerName);
			/* trTag.append(
				String.format(pattern, headerName, headerValue)		
			);
		} */
		%>
		<tr>
			<td><%=headerName %></td>
			<td><%=headerValue %></td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>
</body>
</html>