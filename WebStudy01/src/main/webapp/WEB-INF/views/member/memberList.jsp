<%-- <%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div></div>
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
         List<MemberVO> memberList = (List)request.getAttribute("memberList");
      
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
      <td>회원아이디</td>
      <td>회원명</td>
      <td>이메일</td>
      <td>휴대폰</td>
      <td>지역</td>
      <td>마일리지</td>
   </tbody>
</table>
<!-- 전송이벤트 발생 위해 사용, get 방식으로 넘김 -->
<form id="viewForm" action="<%=request.getContextPath()%>/member/memberView.do">
   <input type="hidden" name="who" />
</form>
<form id="deleteForm" action="<%=request.getContextPath()%>/member/memberDelete.do">
	<input type="hidden" name="who" />
</form>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <!-- 응답데이터 넣어줘야 -->
      <div class="modal-body">
      ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-danger" id="deleteBtn">DELETE</button>
      </div>
    </div>
  </div>
</div>


<script type="text/javascript" src ='<%=request.getContextPath() %>/resources/js/member/memberList.js'>
   
   
   
   //class지워서 동작 안 함
   //DOM트리 전체에 이벤트      특정타겟지칭
   $(document).on("click",".dataTr",function(event){
      let who = $(this).data('who');
      
      viewForm.find('[name=who]').val(who);
      viewForm.submit();
      viewForm.get(0).reset();      <%--뒤로가기했을때 담아놨던걸 없애줘야 하기 때문...
      location.href ="<%=request.getContextPath()%>/member/memberview.do?who"+who; 
      대신 다른 거 써보자, who를 직접 입력해주지 않기 위해서 
      
      
   });
   
/*
   $(".dataTr").on("click",function(){
      alert("alert window.");
      
      location.href(서블릿)
               // /member/memberList.do 절대경로로 이동
   })
*/

</script> --%>
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
        <button type="button" class="btn btn-danger" id="deleteBtn">DELETE</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src='<%=request.getContextPath() %>/resources/js/member/memberList.js'></script>

	