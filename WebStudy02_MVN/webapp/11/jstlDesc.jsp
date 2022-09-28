<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/jstlDesc.jsp</title>
<style type="text/css">
	.yellow{
		background-color: yellow;
	}
	.black{
		background-color: black;
		color: white;
	}
	.red{
		background-color: red;
	}
</style>
</head>
<body>
	<pre>
		: 커스텀 태그 라이브러리
		**taglib 지시자로 해당 커스텀 태그 로딩
		**Core(c) 태그
		1. EL 변수 지원 : set, remove
			<c:set var="sample" scope="session" value="세션샘플"></c:set>
			${sample }
			<c:remove var="sample" scope="session"/>
			${sample }
		2. 조건문 : if, choose~~ when ~~ otherwise
			<c:if test="${not empty sessionScope.sample }">
				샘플있음. -->${sessionScope.sample }
			</c:if>
			<c:if test="${empty sessionScope.sample }">
				샘플없음. 
			</c:if>
			<c:set var="sample" value="샘플" />
			<c:choose>
				<c:when test="${not empty pageScope.sample }">샘플있음</c:when>
				<c:otherwise>샘플없음</c:otherwise>
			</c:choose>
		3. 반복문 : foreach, forTokens
			<c:forEach var="i" begin="1" end="10" step="1" varStatus="vs">
				${i*10 }, ${i*vs.count }
			</c:forEach>
			for(element : collection){}
			<c:set var="elList" value='<%=Arrays.asList("value1", "value2") %>' />
			${elList }
			<c:forEach items="${elList }" var="element" varStatus="vs">
				${element } ${not vs.last? ",":"" }
			</c:forEach>
			
			int num = 4
			intnum=4
			<c:forTokens items="1,2,3" delims="," var="token">
				${token }
			</c:forTokens>
		4. URL 재작성 : url
			<a href="<%=request.getContextPath() %>/prod/prodView.do?what=P101000001">P101000001번 상품 상세조회</a>
			<c:url value="/prod/prodView.do" var="prodViewURL">
				<c:param name="what" value="P101000001" />
				<c:param name="what2" value="P101000002" />
			</c:url>
			<a href="${prodViewURL }">P101000001번 상품 상세조회</a>
			${prodViewURL }
		5. 기타 : redirect, out
			<%--<c:redirect url="/" />--%>
		<c:out value="<table>" />
		첫번째 td 빨강 마지막 td검정색 3번쨰 tr 노란색
	</pre>
	<table border="1">
		<c:forEach var="i" begin="2" end="9" step="1" varStatus="vs1">
			<c:if test="${ vs1.count eq 3}"><c:set var="trClass" value="yellow" /></c:if>
			<c:if test="${ vs1.count ne 3}"><c:set var="trClass" value="normal" /></c:if>
			<tr class="${trClass }"><th>${i }단</th><td>
			<c:forEach var="j" begin="1" end="9" step="1" varStatus="vs2">
				<c:choose>
					<c:when test="${vs2.first}"><c:set var="pClass" value="red" /></c:when>
					<c:when test="${vs2.last }"><c:set var="pClass" value="black" /></c:when>
					<c:otherwise><c:set var="pClass" value="normal" /></c:otherwise>
				</c:choose>
				<p class="${pClass }">${i } * ${j } = ${i*j }</p><br>
			</c:forEach>
			</td></tr>
		</c:forEach>
	</table>
</body>
</html>