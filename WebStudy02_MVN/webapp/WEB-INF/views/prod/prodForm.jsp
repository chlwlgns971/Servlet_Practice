<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form method="post">      
	<input type="hidden" name="prodId" value="${prod.prodId }" />
	<table class="table table-bordered">
		<tr>
			<th>상품명</th>
			<td>
				<input type="text" name="prodName" class="form-control" value="${prod.prodName }" />
				<span class="error">${errors.prodName }</span>
			</td>
		</tr>
		<tr>
			<th class="fst-italic text-danger">분류코드</th>
			<td>
				<select name="prodLgu"  class="form-control">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
					</c:forEach>
				</select>
				<span class="error">${errors.prodLgu }</span>
			</td>
		</tr>
		<tr>
			<th class="fst-italic text-danger">거래처코드</th>
			<td>
				<select name="prodBuyer"  class="form-control">
					<option value>거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">
							${buyer.buyerName }
						</option>
					</c:forEach>
				</select>
				
				<span class="error">${errors.prodBuyer }</span>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>
				<input type="number" name="prodCost" class="form-control"
				 value="${prod.prodCost }" />
				<span class="error">${errors.prodCost }</span>
			</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>
				<input type="number" name="prodPrice" class="form-control"
				 value="${prod.prodPrice }" />
				<span class="error">${errors.prodPrice }</span>
			</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>
				<input type="number" name="prodSale" class="form-control"
				 value="${prod.prodSale }" />
				<span class="error">${errors.prodSale }</span>
			</td>
		</tr>
		<tr>
			<th>개요</th>
			<td>
				<input type="text" name="prodOutline" class="form-control"
				 value="${prod.prodOutline }" />
				<span class="error">${errors.prodOutline }</span>
			</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>
				<input type="text" name="prodDetail" class="form-control"
				value="${prod.prodDetail }" />
				<span class="error">${errors.prodDetail }</span>
			</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
				<input type="text" name="prodImg" class="form-control"
				 value="${prod.prodImg }" />
				<span class="error">${errors.prodImg }</span>
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>
				<input type="number" name="prodTotalstock"
				class="form-control"  value="${prod.prodTotalstock }" />
				<span
				class="error">${errors.prodTotalstock }</span>
			</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>
				<input type="date" name="prodInsdate" class="form-control"
				value="${prod.prodInsdate }" />
				<span class="error">${errors.prodInsdate }</span>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<input type="number" name="prodProperstock"
				class="form-control"  value="${prod.prodProperstock }" />
				<span
				class="error">${errors.prodProperstock }</span>
			</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>
				<input type="text" name="prodSize" class="form-control"
				value="${prod.prodSize }" />
				<span class="error">${errors.prodSize }</span>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<input type="text" name="prodColor" class="form-control"
				value="${prod.prodColor }" />
				<span class="error">${errors.prodColor }</span>
			</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>
				<input type="text" name="prodDelivery" class="form-control"
				value="${prod.prodDelivery }" />
				<span class="error">${errors.prodDelivery }</span>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<input type="text" name="prodUnit" class="form-control"
				value="${prod.prodUnit }" />
				<span class="error">${errors.prodUnit }</span>
			</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>
				<input type="number" name="prodQtyin" class="form-control"
				value="${prod.prodQtyin }" />
				<span class="error">${errors.prodQtyin }</span>
			</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>
				<input type="number" name="prodQtysale" class="form-control"
				value="${prod.prodQtysale }" />
				<span class="error">${errors.prodQtysale }</span>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input type="number" name="prodMileage" class="form-control"
				value="${prod.prodMileage }" />
				<span class="error">${errors.prodMileage }</span>
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
	</script> /
</form>