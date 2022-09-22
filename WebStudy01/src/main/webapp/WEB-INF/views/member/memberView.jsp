<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberVO member = (MemberVO) request.getAttribute("member");
%>
<table>
	<tr>
		<th>회원아이디</th>
		<td><%=member.getMemId()%></td>
	</tr>
	<tr>
		<th>회원 비밀번호</th>
		<td><%=member.getMemPass()%></td>
	</tr>
	<tr>
		<th>회원 이름</th>
		<td><%=member.getMemName()%></td>
	</tr>
	<tr>
		<th>주민번호앞자리</th>
		<td><%=member.getMemRegno1()%></td>
	</tr>
	<tr>
		<th>주민번호뒷자리</th>
		<td><%=member.getMemRegno2()%></td>
	</tr>
	<tr>
		<th>생일</th>
		<td><%=member.getMemBir()%></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td><%=member.getMemZip()%></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><%=member.getMemAdd1()%></td>
	</tr>
	<tr>
		<th>상세주소</th>
		<td><%=member.getMemAdd2()%></td>
	</tr>
	<tr>
		<th>집 전화번호</th>
		<td><%=member.getMemHometel()%></td>
	</tr>
	<tr>
		<th>회사 전화번호</th>
		<td><%=member.getMemComtel()%></td>
	</tr>
	<tr>
		<th>휴대폰 번호</th>
		<td><%=member.getMemHp()%></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><%=member.getMemMail()%></td>
	</tr>
	<tr>
		<th>직업</th>
		<td><%=member.getMemJob()%></td>
	</tr>
	<tr>
		<th>취미</th>
		<td><%=member.getMemLike()%></td>
	</tr>
	<tr>
		<th>기념일</th>
		<td><%=member.getMemMemorial()%></td>
	</tr>
	<tr>
		<th>기념일날짜</th>
		<td><%=member.getMemMemorialday()%></td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td><%=member.getMemMileage()%></td>
	</tr>
</table>