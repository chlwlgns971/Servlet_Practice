<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%	
	String minDan = request.getParameter("minDan");
	String maxDan = request.getParameter("maxDan");

	int min;
	int max;

	if ((minDan != null && !minDan.isEmpty()) && (maxDan != null && !maxDan.isEmpty())) {
		if (!minDan.matches("[1-9]") || !maxDan.matches("[1-9]")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "1단부터 9단까지 범위 내의 파라미터만 가능."); // 오류 코드랑 메시지 보내기 (ex: 404)
			return;
		} else {
			min = Integer.parseInt(minDan);
			max = Integer.parseInt(maxDan);
		}
	} else {
		min = 1;
		max = 9;
	}

	String Pattern = "<option value = %d>%d</option>";

	StringBuffer Options = new StringBuffer();
	for (int i = 1; i <= 9; i++) {
		Options.append(String.format(Pattern, i, i));
	}

	StringBuffer gugudanText = new StringBuffer();
	for (int i = min; i <= max; i++) {
		gugudanText.append(String.format("<tr><th> %d%s </th>", i, "단"));
		for (int j = 1; j <= 9; j++) {
			gugudanText.append(String.format("<td> %d * %d = %d </td>", i, j, i * j));
		}
		gugudanText.append("<tr>");
	}

	String header = request.getHeader("X-Requested-With");
	if ("XMLHttpRequest".equals(header)) {
		out.println(gugudanText);
		return;
	} 
	%>
	
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
	<body>
		<h4>구구단(8*9)</h4>
		2단 부터 9단 까지의 구구단 랜더링.
		출력되는 구구단 text 형식 : "2 * 2 = 4"
		<br><br>
		<form name="gugudanForm">
			최소단 : 
			<select id="minDan" name="minDan" required value="<%= min%>">
				<option>최소단</option>
				<%=Options %>		
			</select>
			
			최대단 :
			<select id="maxDan" name="maxDan" required value="<%= max%>">
				<option>최대단</option>
				<%=Options %>
			</select>
			<input type="submit" value="전송"> 
		</form>
		
		<table border="1" id="gugudanTable">
			<%=gugudanText %>
		</table>
		
		<script>
			$("form:first").on("submit", function(event){
				event.preventDefault(); 
				let url = this.action;
				let method = this.method;
				let data = $(this).serialize(); 
				console.log(url);
				console.log(method);
				console.log(data);
				$.ajax({
					url : url,
					method : method,
					data : data, 
					dataType : "html", 
					success : function(resp){
						$("#gugudanTable").html(resp);
					},
					error : function(errorResp){
						cosole.log(errorResp.status);
					}
				});
				return false; 
			});
		</script>
	</body>
</html>