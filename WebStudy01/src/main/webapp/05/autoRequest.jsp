<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--<meta http-equiv="Refresh" content="1">-->
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<h4>현재 서버의 시간 : <span id="timeArea"></span></h4>
<pre>
	HTTP 특성
		-ConnectLess : request vd response(1:1) 현재 요청에 대한 응답이 전송된 후 연결 종료
		-StateLess : 연결이 종료되면서 양 피어에 저장된 정보들이 삭제된다.
		
		추가적인 자동 요청
		1. server side : Refresh헤더
		2. client side
			1) HTML meta : http-equiv(Refresh)
			2)JavaScript : Scheduling 함수 
							timeout : 지연시간
							interval(*) : 주기적 생신
		<%--
		response.setIntHeader("Refesh",7 );
		--%>
</pre>
<script type="text/javascript">
	let timeArea = $('#timeArea');
	setInterval(function(){
		timeArea.html("");
		//location.reload()
		$.ajax({
			url : "<%=request.getContextPath()%>/getServerTime.html",
			method : "get",
			dataType : "html",
			success : function(resp){
				timeArea.html(resp)
			},
			error : function(error){
				console.log(errorResp.status);
			}
		});
	}, 1000);
</script>
</body>
</html>