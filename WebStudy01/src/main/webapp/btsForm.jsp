<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	function changeSelect(){
		let selected = $("#selectList option:selected").val();
		let area =$('#area');
		$.ajax({
		  // URL은 필수 요소이므로 반드시 구현해야 하는 Property입니다.
			url: "<%=request.getContextPath() %>/bts/getContent", // 요청이 전송될 URL 주소
		 	type: 'GET', // http 요청 방식 (default: ‘GET’)
		  	data: { 'member': selected }, // 요청 시 포함되어질 데이터
		  	dataType: 'json', // 응답 데이터 형식 (명시하지 않을 경우 자동으로 추측)
		  	success: function(data) {
		    	area.html(data);
		  	},
		  	error: function(error) {
		    	// 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에
		    	// error 콜백이 호출될 수 있습니다.
		    	// 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만,
		    	// 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
		  	},
		})
	}
</script>
</head>
<body>
<form action="<%=request.getContextPath() %>/bts/getContent" id="btsForm">
	<select name="member" onchange="$(this.form).submit()" id="selectList">
	<%
         Map<String, String[]> btsDB = (Map) application.getAttribute("btsDB");
         StringBuffer options = new StringBuffer();
         
         btsDB.forEach((k,v)->{
            options.append(String.format("<option value='%s'>%s</option>\n",k,v[0])
            );
//        ??format포맷팅. parse?파싱
         });
         out.print(options);
      %>
	</select>
	<div id="area"></div>
</form>
<script type="text/javascript">
	$(document).on("submit", "form:first", function(event){
		event.preventDefault();
		let form = this;
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
		  // URL은 필수 요소이므로 반드시 구현해야 하는 Property입니다.
			url: url, // 요청이 전송될 URL 주소
		 	type: method, // http 요청 방식 (default: ‘GET’)
		  	data: data, // 요청 시 포함되어질 데이터
		  	dataType: 'html', // 응답 데이터 형식 (명시하지 않을 경우 자동으로 추측)
		  	success: function(resp) {
		    	$(form).after(resp);
		  	},
		  	error: function(error) {
		    	// 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에
		    	// error 콜백이 호출될 수 있습니다.
		    	// 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만,
		    	// 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
		    	console.log(error.status);
		  	},
		})
	})
</script>
</body>
</html>