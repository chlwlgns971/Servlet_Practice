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
   				<div class="pagingArea">
   					${pagingVO.getPagingHTML() }
   				</div>
   				<div id="searchUI">
					<select name="searchType">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="button" value="검색" id="searchBtn" />
				</div>
   			</td>
   		</tr>
   	</tfoot>
</table>
<c:url value="/member/memberView.do" var="memberView" />
<c:url value="/member/memberDelete.do" var="memberDelete" />
<c:url value="/member/memberUpdate.do" var="memberUpdate" />
<form id="searchForm">
	<input type="text" name="page" />
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
</form>
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
<script type="text/javascript">
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(event){
		let inputTags = searchUI.find(":input[name]");
		$.each(inputTags, function(index, inputTag){
			let name = $(this).attr("name");
			let value = $(this).val();
			searchForm.get(0)[name].value = value;
		});
		searchForm.submit();
	});
	let searchForm = $("#searchForm");
	let pageTag = $("[name=page]");
	$("[name=searchType]").val("${pagingVO.simpleCondition.searchType}");
	$("[name=searchWord]").val("${pagingVO.simpleCondition.searchWord}");
	$(".pagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(!page) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
});
</script>

	