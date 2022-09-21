<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)request.getAttribute("member");
%>
<table>
	<thead>
		<tr>
			<td>회원아이디</td>
			<td>회원명</td>
			<td>이메일</td>
			<td>휴대폰</td>
			<td>지역</td>
			<td>마일리지</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><%=member.getMemId() %></td>
			<td><%=member.getMemName() %></td>
			<td><%=member.getMemMail() %></td>
			<td><%=member.getMemHp() %></td>
			<td><%=member.getMemAdd1() %></td>
			<td><%=member.getMemMileage() %></td>
		</tr>
	</tbody>
</table>