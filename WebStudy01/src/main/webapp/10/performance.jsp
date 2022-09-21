<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	long start = System.currentTimeMillis();

	String sql = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID = ?";
	String id = "a001";
	String name = "";
	String headers[] = null;
	for(int j = 1; j <= 100; j++){
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);  // where id=  and passwaord= '1' or '1'='1'  
		){
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
		        headers = new String[count];
		        for(int i = 1; i <= count; i++){
		           headers[i-1] = rsmd.getColumnName(i);
		        }
				if(rs.next()){
					name = rs.getString(headers[0]);
				}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	long end = System.currentTimeMillis();	
	long runtime = end - start;
%>
<!--Model1 구조를 이용하여, 'a001' 사용자의 이름을 조회하라. -> 디자인 고려 x -->

<div><%=name %></div>

<div>코드 실행시간 : <%=runtime %>ms</div>

<h4>한번 연결 수립하고, 한번 쿼리 실행, 한번 출력: 9ms</h4>
<h4>백번 연결 수립하고, 백번 쿼리 실행, 백번 출력: 1138ms</h4>
<h4>한번 연결 수립하고, 백번 쿼리 실행, 백번 출력: 13ms</h4>
<hr/>
<h4>connection pooling 이후</h4>
<h4>한번 연결 수립하고, 한번 쿼리 실행, 한번 출력: ?ms</h4>
<h4>백번 연결 수립하고, 백번 쿼리 실행, 백번 출력: 10ms</h4>
<h4>한번 연결 수립하고, 백번 쿼리 실행, 백번 출력: 5ms</h4>
</body>
</html>