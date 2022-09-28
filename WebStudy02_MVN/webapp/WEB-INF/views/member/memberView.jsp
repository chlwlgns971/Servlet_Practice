<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table>
	<tr>
		<th>회원아이디</th>
		<td>${member['memId'] }</td>
	</tr>
	<tr>
		<th>회원 비밀번호</th>
		<td>${member['memPass'] }</td>
	</tr>
	<tr>
		<th>회원 이름</th>
		<td>${member['memName'] }</td>
	</tr>
	<tr>
		<th>주민번호앞자리</th>
		<td>${member['memRegno1'] }</td>
	</tr>
	<tr>
		<th>주민번호뒷자리</th>
		<td>${member['memRegno2'] }</td>
	</tr>
	<tr>
		<th>생일</th>
		<td>${member['memBir'] }</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${member['memZip'] }</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${member['memAdd1'] }</td>
	</tr>
	<tr>
		<th>상세주소</th>
		<td>${member['memAdd2'] }</td>
	</tr>
	<tr>
		<th>집 전화번호</th>
		<td>${member['memHometel'] }</td>
	</tr>
	<tr>
		<th>회사 전화번호</th>
		<td>${member['memComtel'] }</td>
	</tr>
	<tr>
		<th>휴대폰 번호</th>
		<td>${member['memHp'] }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${member['memMail'] }</td>
	</tr>
	<tr>
		<th>직업</th>
		<td>${member['memJob'] }</td>
	</tr>
	<tr>
		<th>취미</th>
		<td>${member['memLike'] }</td>
	</tr>
	<tr>
		<th>기념일</th>
		<td>${member['memMemorial'] }</td>
	</tr>
	<tr>
		<th>기념일날짜</th>
		<td>${member['memMemorialday'] }</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>${member['memMileage'] }</td>
	</tr>
</table>