<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script> <!-- 바디로 (script 사이로) 파일의 내용을 가져온다. -->
</head>
	<body>
		<form id="factorialForm"> <!-- action x : 현재 창의 주소로 보낸다. -->
			<input type="number" name="operand" value=${number }/>
		</form>
<pre>
10!
1. 반복문 : scriptlet
2. 재귀 호출 : declaration
3. 피연산자 선택 UI
4. Model1 -> Model2 (디자이너와 개발자가 작업하기 편리하다.) : 
- 데이터를 만드는 부분 (Servlet)
- 데이터를 소비하는 부분 (JSP)
- Moder1과 Model2는 코드를 쪼개느냐 합치느냐의 차이일 뿐이다.
5. 비동기
<%-- ${number} ! = ${result} --%>
<span id="resultArea"></span>
</pre>
<script type="text/javascript">
	const PATTERN = "%O! = %R";
	$(":input[name]").on("change", function(){
		$(this).parents("form:first").submit();
		//onchange="this.form.submit();"
	});
	$(document).on("submit", "#factorialForm", function(event){ // 동적으로 form이 바뀔 때 좋은 형태이다.
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize(); // query string
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json",
			success : function(resp) {
				resp.operand;
				resp.result;
				$("#resultArea")
						.html(
							PATTERN.replace("%O", resp.operand)
							.replace("%R", resp.result)
						);
			},
			error : function(errorResp) {
				cosole.log(errorResp.status);
			}
		});
		return false; // 이것만 쓰면 안먹힐 때가 있다.
	});
</script>
	</body>
</html>