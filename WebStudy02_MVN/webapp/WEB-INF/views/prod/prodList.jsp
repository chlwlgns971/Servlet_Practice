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
   <tfoot>
   		<tr>
   			<td colspan="8">
   				<div class="pagingArea">
   					${pagingVO.getPagingHTML() }
   				</div>
   				<div id="searchUI" class="border border-primary border-1 row g-3">
					<h4>검색조건 입력 UI - searchUI</h4>
					<div class="col-auto">
						<select name="prodLgu" class="form-select">
							<option value>상품분류</option>
						</select>
					</div>
					<div class="col-auto">
						<select name="prodBuyer" class="form-select">
							<option value>거래처</option>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="prodName" value="${pagingVO.detailCondition.prodName}" class="form-control" placeholder="상품명"/>
					</div>
					<div class="col-auto">
						<input type="button" class="btn btn-primary" value="검색" id="searchBtn"/>
					</div>
				</div>
   			</td>
   		</tr>
   	</tfoot>
</table>
<form id="searchForm" class="border border-danger border-3">
	<h4>검색조건 전송 UI - searchForm</h4>
	<input type="text" name="page" />
	<input type="text" name="prodLgu" value="${pagingVO.detailCondition.prodLgu}"/>
	<input type="text" name="prodBuyer"  value="${pagingVO.detailCondition.prodBuyer}"/>
	<input type="text" name="prodName"  value="${pagingVO.detailCondition.prodName}"/>
</form>
<script type="text/javascript">
	$.ajax({
		url : "${pageContext.request.contextPath}/prod/getLprodList.do",
		dataType : "json",
		success : function(resp) {
			let lprodList = resp.model;
			let options = [];
			$.each(lprodList, function(index, lprod){
				let option = $("<option>").attr("value", lprod.lprodGu)
											.text(lprod.lprodNm);
				if("${pagingVO.detailCondition.prodLgu}" == lprod.lprodGu){
					option.prop("selected", true);
				}
				
				options.push(option);
			});
			let prodLguTag = searchUI.find("[name=prodLgu]");
			prodLguTag.append(options);
			prodLguTag.trigger("change");
		},
		error : function(errorResp) {
			console.log(errorResp.status);
		}
	});
	
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(event){
		let inputTags = searchUI.find(":input[name]");
		$.each(inputTags, function(index, inputTag){
			let name = $(this).attr("name");
			let value = $(this).val();
			searchForm.get(0)[name].value = value;
		});
		searchForm.submit();
	}).on("change", "[name=prodLgu]", function(event){
		let lprodGu = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/prod/getBuyerList.do",
			data : {
				lprodGu:lprodGu
			},
			dataType : "json",
			success : function(resp) {
				let buyerList = resp.model;
				let options = [];
				$.each(buyerList, function(index, buyer){
					let option = $("<option>").attr("value", buyer.buyerId)
											.addClass(buyer.buyerLgu)
											.text(buyer.buyerName);
					if("${pagingVO.detailCondition.prodBuyer}" == buyer.buyerId){
						option.prop("selected", true);
					}
					options.push(option);
				});
				let prodBuyerTag = searchUI.find("[name=prodBuyer]");
				prodBuyerTag.find("option:not(:first)").remove();
				prodBuyerTag.append(options);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
	});
	
	
	let searchForm = $("#searchForm").on("submit", function(){
		event.preventDefault();//이벤트 중지시키는 메서드
		let url = this.action
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
			url : "${pageContext.request.contextPath}/prod/getBuyerList.do",
			data : {
				
			},
			dataType : "json",
			success : function(resp) {
				
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
		return false;
	}).trigger("submit");
	let pageTag = $("[name=page]");
	
	$(".pagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(!page) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
</script>