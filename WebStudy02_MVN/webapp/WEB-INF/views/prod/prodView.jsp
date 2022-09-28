<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ProdVO prod = (ProdVO)request.getAttribute("prod");
%>
<table class="table table-bordered">
	<tr>
		<th>상품명</th>
		<td><%=prod.getProdName() %></td>
	</tr>
	<tr>
		<th>분류명</th>
		<td><%=prod.getLprodNm() %></td>
	</tr>
	<tr>
		<th>거래처</th>
		<td>
			<table>
				<tr>
					<th>거래처명</th>
					<td>${prod.buyer['buyerName'] }</td>
				</tr>
				<tr>
					<th>소재지</th>
					<td><%=prod.getBuyer().getBuyerAdd1() %></td>
				</tr>
				<tr>
					<th>담당자명</th>
					<td><%=prod.getBuyer().getBuyerCharger() %></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><%=prod.getBuyer().getBuyerComtel() %></td>
				</tr>
				<tr>
					<th>거래은행명</th>
					<td><%=prod.getBuyer().getBuyerBank() %></td>
				</tr>
				<tr>
					<th>은행계좌</th>
					<td><%=prod.getBuyer().getBuyerBankno() %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<th>구매가</th>
		<td><%=prod.getProdCost() %></td>
	</tr>
	<tr>
		<th>판매가</th>
		<td><%=prod.getProdPrice() %></td>
	</tr>
	<tr>
		<th>세일가</th>
		<td><%=prod.getProdSale() %></td>
	</tr>
	<tr>
		<th>개요</th>
		<td><%=prod.getProdOutline() %></td>
	</tr>
	<tr>
		<th>상세정보</th>
		<td><%=prod.getProdDetail() %></td>
	</tr>
	<tr>
		<th>상품이미지</th>
		<td><%=prod.getProdImg() %></td>
	</tr>
	<tr>
		<th>총재고</th>
		<td><%=prod.getProdTotalstock() %></td>
	</tr>
	<tr>
		<th>입고일</th>
		<td><%=prod.getProdInsdate() %></td>
	</tr>
	<tr>
		<th>적정재고</th>
		<td><%=prod.getProdProperstock() %></td>
	</tr>
	<tr>
		<th>크기</th>
		<td><%=prod.getProdSize() %></td>
	</tr>
	<tr>
		<th>색상</th>
		<td><%=prod.getProdColor() %></td>
	</tr>
	<tr>
		<th>배송방법</th>
		<td><%=prod.getProdDelivery() %></td>
	</tr>
	<tr>
		<th>단위</th>
		<td><%=prod.getProdUnit() %></td>
	</tr>
	<tr>
		<th>입고량</th>
		<td><%=prod.getProdQtyin() %></td>
	</tr>
	<tr>
		<th>판매량</th>
		<td><%=prod.getProdQtysale() %></td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td><%=prod.getProdMileage() %></td>
	</tr>
	<tr>
		<th>구매자 정보</th>
		<td>
			<table>
		<%
			for(MemberVO member : prod.getMemberList()){
		%>
			<tr>
			<td><%=member.getMemId() %></td>
			<td><%=member.getMemName() %></td>
			<td><%=member.getMemAdd1() %></td>
			<td><%=member.getMemMileage() %></td>
			</tr>
		<%
			}
		%>
			</table>
		</td>
	</tr>
</table>