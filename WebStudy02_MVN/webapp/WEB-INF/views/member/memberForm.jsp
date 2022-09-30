<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="error">${message }</div>
<h4>가입양식</h4>
<form method="post">
	<table class="table table-bordered">
		<tr>
			<th>회원ID</th>
			<td>
				<input type="text" name="memId" class="form-control" value="${member['memId'] }" />
				<span class="error">${errors['memId'] }</span>
			</td>
		</tr>
		<tr>
			<th>MEM_PASS</th>
			<td>
				<input type="text" name="memPass" class="form-control" value="${member['memPass'] }" />
				<span class="error">${errors['memPass'] }</span>
			</td>
		</tr>
		<tr>
			<th>회원이름</th>
			<td>
				<input type="text" name="memName" class="form-control" value="${member['memName'] }" />
				<span class="error">${errors['memName'] }</span>
			</td>
		</tr>
		<tr>
			<th>주민번호 앞자리</th>
			<td><input type="text" name="memRegno1" class="form-control"
				value="${member['memRegno1'] }" /><span class="error">${errors['memRegno1'] }</span></td>
		</tr>
		<tr>
			<th>주민번호뒷자리</th>
			<td><input type="text" name="memRegno2" class="form-control"
				value="${member['memRegno2'] }" /><span class="error">${errors['memRegno2'] }</span></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="date" name="memBir" class="form-control"
				value="${member['memBir'] }" /><span class="error">${errors['memBir'] }</span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="memZip" class="form-control editable"
				value="${member['memZip'] }" /><span class="error">${errors['memZip'] }</span></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" name="memAdd1" class="form-control editable"
				value="${member['memAdd1'] }" /><span class="error">${errors['memAdd1'] }</span></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" name="memAdd2" class="form-control editable"
				value="${member['memAdd2'] }" /><span class="error">${errors['memAdd2'] }</span></td>
		</tr>
		<tr>
			<th>집전화번호</th>
			<td><input type="text" name="memHometel" class="form-control editable"
				value="${member['memHometel'] }" /><span class="error">${errors['memHometel'] }</span></td>
		</tr>
		<tr>
			<th>회사번호</th>
			<td><input type="text" name="memComtel" class="form-control editable"
				value="${member['memComtel'] }" /><span class="error">${errors['memComtel'] }</span></td>
		</tr>
		<tr>
			<th>이동전화번호</th>
			<td><input type="text" name="memHp" class="form-control editable"
				value="${member['memHp'] }" /><span class="error">${errors['memHp'] }</span></td>
		</tr>
		<tr>
			<th>이메일주소</th>
			<td><input type="text" name="memMail" class="form-control editable"
				value="${member['memMail'] }" /><span class="error">${errors['memMail'] }</span></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="memJob" class="form-control editable"
				value="${member['memJob'] }" /><span class="error">${errors['memJob'] }</span></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="memLike" class="form-control editable"
				value="${member['memLike'] }" /><span class="error">${errors['memLike'] }</span></td>
		</tr>
		<tr>
			<th>기념일명</th>
			<td><input type="text" name="memMemorial" class="form-control editable" value="${member['memMemorial'] }" />
			<span class="error">${errors['memMemorial'] }</span></td>
		</tr>
		<tr>
			<th>기념일날짜</th>
			<td>
				<input type="date" name="memMemorialday"class="form-control editable" value="${member['memMemorialday'] }" />
				<span class="error">${errors['memMemorialday'] }</span>
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
<%
	if("UPDATE".equals(request.getAttribute("command"))){
%>
<script type="text/javascript">
	$(":input:not(.editable)").prop("readonly",true);
</script>
<%
	}
%>