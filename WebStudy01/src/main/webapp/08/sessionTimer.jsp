<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/sessionTimer.jsp</title>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript">
	var sessionTime =<%=session.getMaxInactiveInterval()%>
	function setTimer() {
		sessionTime--;

		$("#timerArea").text(sessionTime);
	}
	$(document).ready(function() {
		setInterval("setTimer()", 1000)
	})
</script>
</head>
<body>
	<h4>세션 타이머</h4>
	<div id="timerArea"></div>
	<!-- 세션 타이머가 실시간으로 줄어드는 것 구현 -->
	<!--  -->
	<div id="msgArea">
		세션 만료시간이 1분 남았음. 연장 할래? <input type="button" value="YES"
			class="ctrlBtn"> <input type="button" value="NO"
			class="ctrlBtn">
	</div>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-bs-toggle="modal"
		data-bs-target="#exampleModal">Launch demo modal</button>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>

1. session timeout 초기값으로 갖는 타이머 출력.

2. 1초를 주기로 타이머를 디스카운트. -> 타이머가 0이되면 디스카운트 중단.

3. timeout -> 60초 동안 대기한 후 메세지 창(modal) 디스플레이

4-1. No버튼 클릭 -> 메시지창 닫기
4-2. Yes버튼 클릭
	1) 서버의 세션을 연장하기 위한 비동기 요청 : response body가 없도록 method설정.
	2) 타이머 리셋.
	3) 메세지 창 닫기