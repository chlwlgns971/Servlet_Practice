<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
	    $('.info').on('click', function () {
	    	let id = $(this).data('what');
	    	location.href="<%=request.getContextPath()%>/prod/prodView.do?what="+id;
	    });
  	});
</script>
<table class="table table-bordered">
   <thead>
      <tr>
		<td>상품명</td>
		<td>분류명</td>
		<td>판매가</td>
		<td>매입가</td>
		<td>마일리지</td>
		<td>거래처명</td>
		<td>구매자수</td>
      </tr>
   </thead>
   <tbody>
      <%
         List<ProdVO> prodList = (List)request.getAttribute("prodList");
         if(prodList.isEmpty()){
            %>
            <tr>
               <td colspan="6">정보가 없음.</td>
            </tr>
            <%
         }else{
            for(ProdVO prod : prodList){
               %>
               <tr data-what="<%=prod.getProdId() %>" class="info">
                  <td><%=prod.getProdName() %></td>
                  <td><%=prod.getLprodNm() %></td>
                  <td><%=prod.getProdPrice() %></td>
                  <td><%=prod.getProdCost() %></td>
                  <td><%=prod.getProdMileage() %></td>
                  <td><%=prod.getBuyer().getBuyerName() %></td>
                  <td><%=prod.getMemCount() %></td>
               </tr>
               <%
            }
         }
      %>
   
   </tbody>
</table>