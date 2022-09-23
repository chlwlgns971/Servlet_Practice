<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="table table-bordered">
   <thead>
      <tr>
         <th>회원아이디</th>
         <th>회원명</th>
         <th>이메일</th>
         <th>휴대폰</th>
         <th>지역</th>
         <th>마일리지</th>
      </tr>
   </thead>
   <tbody>
      <%
         List<MemberVO> memberList = (List) request.getAttribute("memberList");
         if(memberList.isEmpty()){
            %>
            <tr>
               <td colspan="6">회원이 없음.</td>
            </tr>
            <%
         }else{
            for(MemberVO member : memberList){
               %>
               <tr data-who="<%=member.getMemId() %>" 
                     data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <td><%=member.getMemId() %></td>
                  <td><%=member.getMemName() %></td>
                  <td><%=member.getMemMail() %></td>
                  <td><%=member.getMemHp() %></td>
                  <td><%=member.getMemAdd1() %></td>
                  <td><%=member.getMemMileage() %></td>
               </tr>
               <%
            }
         }
      %>
   
   </tbody>
</table>
<form id='viewForm' action="<%=request.getContextPath() %>/member/memberView.do">
   <input type='hidden' name='who'  />
</form>
<form id="deleteForm" action="<%=request.getContextPath() %>/member/memberDelete.do" method="post">
   <input type='hidden' name='who'  />
</form>
<form id="updateForm" action="<%=request.getContextPath() %>/member/memberUpdate.do" method="get">
   <input type='hidden' name='who'  />
</form>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-danger" id="updateBtn">UPDATE</button>
        <button type="button" class="btn btn-danger" id="deleteBtn">DELETE</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src='<%=request.getContextPath() %>/resources/js/member/memberList.js'></script>

	