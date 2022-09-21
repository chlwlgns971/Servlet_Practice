<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script>
    	$(document).on('click','.dataTr', function(){
    		let data = $(this).data('who');
    		location.href = "<%=request.getContextPath()%>/member/memberView.do?memId="+data;
   			<%-- $.ajax({
               	url : "<%=request.getContextPath()%>/member/memberView.do",     // 컨트롤러에서 대기중인 URL 주소이다.
              	data : {"memId" : data},            // Json 형식의 데이터이다.
               	success : function(res){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                   // 응답코드 > 0000
                   	alert(res.code);
               	},
   		 	}) --%>
    		
    	})
    </script>
	<table border="1">
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
			<%
				List<MemberVO> memberList = (List)request.getAttribute("memberList");
				if(memberList.isEmpty()){
					%>
					<tr>
						<td colspan="6">회원이 없음.</td>
					</tr>
					<% 
				}
				else{
					for(MemberVO vo : memberList){
			%>
			<!-- class="dataTr" -->
			<tr  data-who="<%=vo.getMemId() %>" data-bs-toggle="modal" data-bs-target="#exampleModal">
				<td><%=vo.getMemId() %></td>
				<td><%=vo.getMemName() %></td>
				<td><%=vo.getMemMail() %></td>
				<td><%=vo.getMemHp() %></td>
				<td><%=vo.getMemAdd1() %></td>
				<td><%=vo.getMemMileage() %></td>
			</tr>
			<%
					}
				}
			%>
		</tbody>
	</table>
	