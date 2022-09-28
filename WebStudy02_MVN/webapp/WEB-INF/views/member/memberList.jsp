<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
   		<c:set var="memberList" value="${pagingVO.dataList }" />
   	  	<c:choose>
   	  	 	<c:when test="${empty memberList }">
   	  	 		<tr><td colspan="6">회원이 없음.</td></tr>
   	  	 	</c:when>
   	  	 	<c:otherwise>
   	  	 		<c:forEach var="member" items="${ memberList}">
   	  	 			<tr data-who="${member['memId'] }" data-bs-toggle="modal" data-bs-target="#exampleModal">
   	  	 				<td>${member['memId'] }</td>
                  		<td>${member['memName'] }</td>
                  		<td>${member['memMail'] }</td>
                  		<td>${member['memHp'] }</td>
                  		<td>${member['memAdd1'] }</td>
                  		<td>${member['memMileage'] }</td>
   	  	 			</tr>
   	  	 		</c:forEach>
   	  	 	</c:otherwise>
   	  	</c:choose>
   	</tbody>
   	<tfoot>
   		<tr>
   			<td colspan="7">
   				${pagingVO.getPagingHTML() }
   			</td>
   		</tr>
   	</tfoot>
</table>
<c:url value="/member/memberView.do" var="memberView" />
<c:url value="/member/memberDelete.do" var="memberDelete" />
<c:url value="/member/memberUpdate.do" var="memberUpdate" />
<form id='viewForm' action="${memberView }">
   <input type='hidden' name='who'  />
</form>
<form id="deleteForm" action="${memberDelete }" method="post">
   <input type='hidden' name='who'  />
</form>
<form id="updateForm" action="${memberUpdate }" method="get">
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

	