<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberVO member = (MemberVO) request.getAttribute("member");
if (member == null)
	member = new MemberVO();

Map<String, String> errors = (Map) request.getAttribute("errors");
if (errors == null)
	errors = new HashMap<>();

String message = (String) request.getAttribute("message");
if (message != null && !message.isEmpty()) {
%>
<div class="error"><%=message%></div>
<%
}
%>
<h4>가입양식</h4>
<form action="<%=request.getContextPath()%>/member/memberInsert.do"
	method="post">
	<table>
		<tr>
			<th>회원ID</th>
			<td>
				<input type="text" name="memId" class="form-control" required value="<%=member.getMemId()%>" />
				<span class="error"><%=errors.get("memId")%></span>
			</td>
		</tr>
		<tr>
			<th>MEM_PASS</th>
			<td>
				<input type="text" name="memPass" class="form-control" value="<%=member.getMemPass()%>" />
				<span class="error"><%=errors.get("memPass")%></span>
			</td>
		</tr>
		<tr>
			<th>회원이름</th>
			<td>
				<input type="text" name="memName" class="form-control" value="<%=member.getMemName()%>" />
				<span class="error"><%=errors.get("memName")%></span>
			</td>
		</tr>
		<tr>
			<th>주민번호 앞자리</th>
			<td><input type="text" name="memRegno1" class="form-control"
				value="<%=member.getMemRegno1()%>" /><span class="error"><%=errors.get("memRegno1")%></span></td>
		</tr>
		<tr>
			<th>주민번호뒷자리</th>
			<td><input type="text" name="memRegno2" class="form-control"
				value="<%=member.getMemRegno2()%>" /><span class="error"><%=errors.get("memRegno2")%></span></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="date" name="memBir" class="form-control"
				value="<%=member.getMemBir()%>" /><span class="error"><%=errors.get("memBir")%></span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="memZip" class="form-control"
				value="<%=member.getMemZip()%>" /><span class="error"><%=errors.get("memZip")%></span></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" name="memAdd1" class="form-control"
				value="<%=member.getMemAdd1()%>" /><span class="error"><%=errors.get("memAdd1")%></span></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" name="memAdd2" class="form-control"
				value="<%=member.getMemAdd2()%>" /><span class="error"><%=errors.get("memAdd2")%></span></td>
		</tr>
		<tr>
			<th>집전화번호</th>
			<td><input type="text" name="memHometel" class="form-control"
				value="<%=member.getMemHometel()%>" /><span class="error"><%=errors.get("memHometel")%></span></td>
		</tr>
		<tr>
			<th>회사번호</th>
			<td><input type="text" name="memComtel" class="form-control"
				value="<%=member.getMemComtel()%>" /><span class="error"><%=errors.get("memComtel")%></span></td>
		</tr>
		<tr>
			<th>이동전화번호</th>
			<td><input type="text" name="memHp" class="form-control"
				value="<%=member.getMemHp()%>" /><span class="error"><%=errors.get("memHp")%></span></td>
		</tr>
		<tr>
			<th>이메일주소</th>
			<td><input type="text" name="memMail" class="form-control"
				value="<%=member.getMemMail()%>" /><span class="error"><%=errors.get("memMail")%></span></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="memJob" class="form-control"
				value="<%=member.getMemJob()%>" /><span class="error"><%=errors.get("memJob")%></span></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="memLike" class="form-control"
				value="<%=member.getMemLike()%>" /><span class="error"><%=errors.get("memLike")%></span></td>
		</tr>
		<tr>
			<th>기념일명</th>
			<td><input type="text" name="memMemorial" class="form-control"
				value="<%=member.getMemMemorial()%>" /><span class="error"><%=errors.get("memMemorial")%></span></td>
		</tr>
		<tr>
			<th>기념일날짜</th>
			<td>
				<input type="date" name="memMemorialday"class="form-control" value="<%=member.getMemMemorialday()%>" />
				<span class="error"><%=errors.get("memMemorialday")%></span>
			</td>
		</tr>
		<tr>
			<td>
				<input type="reset" value="취소" class="btn btn-warning" /> 
				<input type="submit" value="저장" class="btn btn-primary" />
			</td>
		</tr>

	</table>
</form>