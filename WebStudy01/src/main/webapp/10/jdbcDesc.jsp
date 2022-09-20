<%@page import="kr.or.ddit.props.vo.PropertyVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="com.fasterxml.jackson.databind.PropertyName"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/jspDesc.jsp</title>
</head>
<body>
<pre>
	1. 드라이버 빌드 패스에 추가(vendor 제공)
	2. 드라이버 로딩(어플리케이션 시작 시점에 한번)
	3. Connection 수립
	4. 쿼리 객체
		Statement
		PreparedStatement(선컴파일된 쿼리 객체)
		CallableStatement(프로시저나 함수와 같은 절차 코드 호출에 사용)
	5. 쿼리 실행
		ResultSet executeQuery : SELECT
		int executeUpdate : INSERT/UPDATE/DELETE
	6. 쿼리 실행 결과 처리
	7. 자원 해제(***) -> db에 제한한 쿼리만을 처리할 수 있다. 따라서 close없이 계속 생성해서 사용하면 더이상 처리할 수 없게 된다.
</pre>
<table border="1">
	<tr>
		<td><%=headers[0] %></td>
		<td><%=headers[1] %></td>
		<td><%=headers[2] %></td>
	</tr>
	<%
		//for(int i = 0; i < propertyName.size(); i++){
		for(PropertyVO vo : dataList){
	%>
		<tr>
			<td><%=vo.getPropertyName() %></td>
			<td><%=vo.getPropertyValue() %></td>
			<td><%=vo.getDescription() %></td>
		</tr>
	<%
		}
	%>
</table>
<%!
	private Connection oracleConn;
	private Statement oracleStmt;
	List<String> propertyName = new ArrayList<>();
	List<String> propertyValue = new ArrayList<>();
	List<String> description = new ArrayList<>();
	PropertyVO vo = null;
	List<PropertyVO> dataList = new ArrayList<>();
	ResultSetMetaData rsmd = null;
	String[] headers = null;
%>
<%
	String oracleURL = "jdbc:oracle:thin:@//localhost:1521/XE";
	String oracleUser = "ddit";
	String oraclePassword = "java";
	
 	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	String sql = "select property_name, property_value, description from database_properties";
	
	try(
		Connection oracleConn = DriverManager.getConnection(oracleURL, oracleUser, oraclePassword);
		Statement oracleStmt = oracleConn.createStatement();	
	){	
		ResultSet rs = oracleStmt.executeQuery(sql);
		rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		headers = new String[count];
		vo = new PropertyVO();
		for(int i = 0; i <= count; i++) {
			headers[i-1] = rsmd.getColumnName(i);
		}
		while(rs.next()) {
			//propertyName.add(rs.getString("property_name"));
			//propertyValue.add(rs.getString("property_value"));
			//description.add(rs.getString("description"));
			dataList.add(vo);
			vo.setPropertyName(rs.getString("PROPERTY_NAME"));
			vo.setPropertyName(rs.getString("PROPERTY_VALUE"));
			vo.setPropertyName(rs.getString("DESCRIPTION"));
		}
		System.out.println(propertyName);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>
</body>
</html>