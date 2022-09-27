<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ProdVO prod = (ProdVO) request.getAttribute("prod");
	if(prod==null)
		prod = new ProdVO();
	Map<String,String> errors = (Map) request.getAttribute("errors");
	if(errors==null)
			errors = new HashMap<>();
%>
<form method="post">      
	<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td>
				<input type="text" name="prodId" class="form-control"
				required value="<%=prod.getProdId()%>" />
				<span class="error"><%=errors.get("prodId")%></span>
			</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>
				<input type="text" name="prodName" class="form-control"
				required value="<%=prod.getProdName()%>" />
				<span class="error"><%=errors.get("prodName")%></span>
			</td>
		</tr>
		<tr>
			<th class="fst-italic text-danger">분류코드</th>
			<td>
				<%--
				<input type="text" name="prodLgu" class="form-control"
				required value="<%=prod.getProdLgu()%>" />
				--%>
				
				<select name="prodLgu" required class="form-control">
					<option value>상품분류</option>
				<%
					List<Map<String,Object>> lprodList = (List) request.getAttribute("lprodList");
					for(Map<String,Object> lprod : lprodList){
						%>
						<option value="<%=lprod.get("lprodGu")%>"><%=lprod.get("lprodNm") %></option>
						<%						
					}
				%>
				</select>
				<span class="error"><%=errors.get("prodLgu")%></span>
			</td>
		</tr>
		<tr>
			<th class="fst-italic text-danger">거래처코드</th>
			<td>
				<%--
				<input type="text" name="prodBuyer" class="form-control"
				required value="<%=prod.getProdBuyer()%>" />
				--%>
				<select name="prodBuyer" required class="form-control">
					<option value>거래처</option>
				<%
					List<BuyerVO> buyerList = (List) request.getAttribute("buyerList");
					for(BuyerVO buyer : buyerList){
						%>
						<option value="<%=buyer.getBuyerId() %>" class="<%=buyer.getBuyerLgu()%>">
							<%=buyer.getBuyerName() %>
						</option>
						<%						
					}
				%>
				</select>
				
				<span class="error"><%=errors.get("prodBuyer")%></span>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>
				<input type="number" name="prodCost" class="form-control"
				required value="<%=prod.getProdCost()%>" />
				<span class="error"><%=errors.get("prodCost")%></span>
			</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>
				<input type="number" name="prodPrice" class="form-control"
				required value="<%=prod.getProdPrice()%>" />
				<span class="error"><%=errors.get("prodPrice")%></span>
			</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>
				<input type="number" name="prodSale" class="form-control"
				required value="<%=prod.getProdSale()%>" />
				<span class="error"><%=errors.get("prodSale")%></span>
			</td>
		</tr>
		<tr>
			<th>개요</th>
			<td>
				<input type="text" name="prodOutline" class="form-control"
				required value="<%=prod.getProdOutline()%>" />
				<span class="error"><%=errors.get("prodOutline")%></span>
			</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>
				<input type="text" name="prodDetail" class="form-control"
				value="<%=prod.getProdDetail()%>" />
				<span class="error"><%=errors.get("prodDetail")%></span>
			</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
				<input type="text" name="prodImg" class="form-control"
				required value="<%=prod.getProdImg()%>" />
				<span class="error"><%=errors.get("prodImg")%></span>
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>
				<input type="number" name="prodTotalstock"
				class="form-control" required value="<%=prod.getProdTotalstock()%>" />
				<span
				class="error"><%=errors.get("prodTotalstock")%></span>
			</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>
				<input type="date" name="prodInsdate" class="form-control"
				value="<%=prod.getProdInsdate()%>" />
				<span class="error"><%=errors.get("prodInsdate")%></span>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<input type="number" name="prodProperstock"
				class="form-control" required value="<%=prod.getProdProperstock()%>" />
				<span
				class="error"><%=errors.get("prodProperstock")%></span>
			</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>
				<input type="text" name="prodSize" class="form-control"
				value="<%=prod.getProdSize()%>" />
				<span class="error"><%=errors.get("prodSize")%></span>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<input type="text" name="prodColor" class="form-control"
				value="<%=prod.getProdColor()%>" />
				<span class="error"><%=errors.get("prodColor")%></span>
			</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>
				<input type="text" name="prodDelivery" class="form-control"
				value="<%=prod.getProdDelivery()%>" />
				<span class="error"><%=errors.get("prodDelivery")%></span>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<input type="text" name="prodUnit" class="form-control"
				value="<%=prod.getProdUnit()%>" />
				<span class="error"><%=errors.get("prodUnit")%></span>
			</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>
				<input type="number" name="prodQtyin" class="form-control"
				value="<%=prod.getProdQtyin()%>" />
				<span class="error"><%=errors.get("prodQtyin")%></span>
			</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>
				<input type="number" name="prodQtysale" class="form-control"
				value="<%=prod.getProdQtysale() %>" />
				<span class="error"><%=errors.get("prodQtysale") %></span>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input type="number" name="prodMileage" class="form-control"
				value="<%=prod.getProdMileage() %>" />
				<span class="error"><%=errors.get("prodMileage") %></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="reset" class="btn btn-danger" value="취소" />
				<input type="submit" class="btn btn-success" value="전송" />
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		let prodBuyerTag = $("[name=prodBuyer]");
		$("[name=prodLgu]").on("change", function(event){
			let lgu = $(this).val();
			prodBuyerTag.find("option."+lgu).show();
			prodBuyerTag.find("option:first").show();
			prodBuyerTag.find("option:not(."+lgu+")").hide();
		});
	</script>
</form>