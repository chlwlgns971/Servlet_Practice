<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/elDesc.jsp</title>
</head>
<body>
	<h4>EL(Expression Language: 표현언어)</h4>
	<!-- 두개가 같은 역할을 함. 단 el은 값이 null일 경우 출력을 안한다. -->
	<pre>
		:표현식의 대체로 Scope 통해 공유되고 있는 속성(attribute)데이터를 출력하기 위해 사용.
		<%
			request.setAttribute("sample", "");
			session.setAttribute("sample", "세션속성");	
			pageContext.setAttribute("list", Arrays.asList(""));
		%>
		${sample }, <%=request.getAttribute("sample") %> 
		${sample }, <%=session.getAttribute("sample") %> 
		${sessionScope.sample }, <%=session.getAttribute("sample") %> 
		
		1. EL 연산자
			산술연산자 : ${3+4 }, ${num+2 }, ${"3"+4 }, \${"a"+4 }
					  ${num-2 }, ${3/4 }
					  <!-- 스크립트 언어에서 정의되지 않은 변수에 논리연산자를 붙이면 존재하지 않으므로 false로 취급 -->
			논리연산자 : ${true and true }, ${alpha or false }, ${not alpha }
			비교연산자 : eq(equal), ne(not equal), gt(grater than), lt, ge, le 
						${3 le 4 }, ${2 eq 2 }, ${3 ne 2 }
			단항연산자 : empty <!-- el 5버전부턴 ++/--도 지원한다. but 지금쓰고 있는 el은 3버전 . 또 컬렉션은 사이즈를 체킹한다. -->
					${empty alpha }, ${empty sample }, ${empty list }
			삼항연산자 : 조건식? 참표현:거짓표현
					${empty alpha ? "비어있음" : "채워있음" }
					${empty list ? "비어있음" : "채워있음" }
					${not empty list ? "비어있음" : "채워있음" }
		2. EL 에서 객체 사용
			<%
				MemberVO member = new MemberVO();
				member.setMemName("김은대");
				pageContext.setAttribute("member", member);
			%>
			<!-- 눈으로 보기엔 memName을 직접 호출하는 것처럼 보이지만 실제론 getter을 이용하여 불러온다. -->
			${member.memName }, ${member.getMemName() }
			${member.getMemTest() }, ${member.memTest } <!-- el에서 자동으로 추측해서 가져옴 -->, ${member['memTest'] }
		3. EL 에서 컬렉션 사용
		4. EL 기본 객체
			scope 객체 : pageScope, requestScope, sessionScope, applicationScope
					${sessionScope.sample }, ${sessionScope['sample'] }
			request parameter 객체: param, paramValues
				<%=request.getParameter("param1") %>
				${param.param1 }, ${param['param1'] }
				<%=request.getParameterValues("param1") %>
				${paramValues.param1[1] }, ${paramValues['param1'][0] }
			request header객체 : header(Map&gt; String, Stirng&lt;), headerValues(Map&gt;String,String&lt;)
				<%=request.getHeader("Accept") %>
				${header.accept }, ${header['accept'] }
				<%=request.getHeader("user-agent") %>
				${header.user-agent }, ${header['user-agent'] }
			cookie 객체 : cookie
				<%=request.getCookies() %>
				${cookie.JSESSISONID }, ${cookie.JSESSISONID.value }
				${cookie['JSESSISONID'] }, ${cookie['JSESSISONID']['value'] }
			pageContext : ${ }
	</pre>
<script type="text/javascript">
	let sample = "스크립트변수";
	alert(`변수의 값: \${sample}`);
</script>
</body>
</html>