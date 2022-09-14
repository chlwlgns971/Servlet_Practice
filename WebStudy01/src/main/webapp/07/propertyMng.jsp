<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<select id="propSel" size="10"></select>
<table>
	<thead>
		<tr>
			<th>프로퍼티명</th>
			<th>프로퍼티값</th>
			<th>비고</th>
		</tr>
	</thead>
	<tbody id="dataArea">
	
	</tbody>
</table>
<form>
	<input type="text" name="propertyName" placeholder="포르퍼티명"/>
	<input type="text" name="propertyValue" placeholder="포르퍼티값"/>
	<input type="submit" value="새 프로퍼티 추가"/>
</form>
<div id="errArea"></div>
<script type="text/javascript">
// 	다음의 모든 요청은 비동기 처리를 기반으로 함.
// 	1. 현재 페이지가 랜더링된 후 전체 프로퍼티 정보를 조회하여 select option 을 완성함. line : /properties (GET)
// 	2. 선택 option이 변경될때마다 해당 프로퍼티의 정보를 조회하여 dataArea 에 랜더링함. line : /property?name=prop1 (GET)
// 	3. 하단 form 을 비동기로 전송하여 새 프로퍼티를 추가하고, 기존의 option들의 앞메 추가함. line : /property (POST)
// 	4. 모든 message(content)는 "json" 형식으로 교환됨.
// 	5. 요청 처리에 실패한 경우, 해당 상태코드와 응답 메시지를 errArea 에 랜더링함.

$(document).ready(function(){
	setSelectOption();
})

function setSelectOption(){
	$.ajax({
		url : "<%=request.getContextPath()%>/propertiesControllerService",
		method : "GET",
		dataType : "json",
		success : function(resp) {
			$.each(resp,function(k,v){
				var option = $("<option id='"+k+"'>"+v+"</option>");                
				$('#propSel').append(option);
			})
		},
		error : function(error) {
			console.log(errorResp.status);
		}
	});
}
</script>
</body>
</html>