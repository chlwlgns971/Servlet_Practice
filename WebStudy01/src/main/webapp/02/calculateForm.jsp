<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<pre>
- 이항 연산자로 4칙 연산 처리하기
1. 동기 처리
2. 비동기 처리
</pre>
<form action="<%=request.getContextPath()%>/calculate"> 
	<input type="number" name="leftOp"/>
	<select name="operator">
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>
	<input type="number" name="rightOp"/>
	<input type="submit" value="="/>
</form>
결과
<span id="resultArea"></span>
</body>
</html>