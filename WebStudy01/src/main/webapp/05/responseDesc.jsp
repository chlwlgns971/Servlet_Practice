<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/responseDesc.jsp</title>
</head>
<body>
<h4>response (HttpServletResponse)</h4>
<pre>
	: 서버가 클라이언트쪽으로 전송하는 컨텐츠에 대한 모든 정보를 가진 객체.
	
	Http의 response packaging
	1. Response Line : Status Code, Protocol/version
		Status Code : 요청 처리 성공 여부를 알려주는 상태코드(ex. 404 Not Found, Not Exist)
			-100~ : ING...(WebSocket을 이용한 실시간 양방향 처리), WebRTC
			-200~ : 응답성공
			-300~ : 클라이언트의 추가 액션이 필요함.
				-304(Not Modified)
				-307(Moved)-Location헤더와 병행. Redirect 이동구조에서 활용
			-400~ : 클라이언트측 오류로 실패
				-404(Not Found, Not Exist)
				-405(Method Not Allowed) : 서블릿을 만들고 오버라이딩을 하지 않으면 발생
				-400(Bad Request) : 필수 데이터 누락, 데이터 타입 부적절, 데이터 길이 부적절
				-401(UnAuthorized)/403(Forbidden) : 보안 처리를 위한 접근제어.
				-406(Not Acceptable , request Accept Header) : 서버가 해당 컨텐츠 타입을 처리할 수 없음. 
				-415(UnSupported Media Type, request content-Type header) : 서버가 요청의 포함된 컨탠츠를 처리할 수 없을 때 발생
			-500~ : 서버측 오류로 실패 
			-> 400번대는 클라이언트 오류로 최대한 상세하게 오류 내역을 알려주지만 
			500번대는 서버오류로 클라이언트에 서버내용을 감추기 위해서 500번으로 일괄표현한다.
	2. Response Header -> Contents metadata
		Response Header에 포함되는 메타데이터들.
		-Content-Type(request Accept header pair)
		-Content-Length
		-Refresh(Reload) : 자동요청을 발생시킬 수 있다.
		-Cache-XXX : 캐시의 사용유무/ 캐시유지 시간 등을 설정
	3. Response Body(Content Body, Message Body) -> Contents
</pre>
</body>
</html>