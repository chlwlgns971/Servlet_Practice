package kr.or.ddit.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.Module.SetupContext;

public class DataBaseConnectionTest {
	private Connection oracleConn;
	private Connection mariaDBConn;
	private Statement oracleStmt;
	private Statement mariaDBStmt;
	
	@BeforeClass //테스트의 갯수 상관없이 실행 후 딱 한번만 실행
	public static void setUpClass() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before //테스트의 갯수 만큼 실행 전 실행
	public void setUp() {
		String oracleURL = "jdbc:oracle:thin:@//localhost:1521/XE";
		String oracleUser = "ddit";
		String oraclePassword = "java";
		
		String mariaDBURL="jdbc:mariadb://localhost:3305/python";//mariaDB는 DB이름이 들어감.
		String mariaDBUser = "root";
		String mariaDBPassword = "python";
		try {
			oracleConn=DriverManager.getConnection(oracleURL, oracleUser, oraclePassword);
			mariaDBConn=DriverManager.getConnection(mariaDBURL, mariaDBUser, mariaDBPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOracle() {
		String sql = "SELECT MEM_NAME FROM MEMBER";
		System.out.println("oracleConn");
		try {
			oracleStmt = oracleConn.createStatement();
			ResultSet rs = oracleStmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("test");
			System.out.println(rsmd.getColumnName(1));
			while(rs.next()) {
				System.out.println(rs.getString("MEM_NAME"));
			}
			System.out.println(oracleConn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMariaDB() {
		String sql = "select e_name from emp";
		System.out.println("mariaDBConn");
		try {
			mariaDBStmt = mariaDBConn.createStatement();
			ResultSet rs = mariaDBStmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("e_name"));
			}
			System.out.println(mariaDBConn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After //테스트의 갯수 만큼 실행 후 실행
	public void tearDown() {
		try {
			if(oracleStmt != null) {
				oracleStmt.close();
			}
			if(mariaDBStmt != null) {
				mariaDBStmt.close();
			}
			if(oracleConn != null) {
				oracleConn.close();
			}
			if(mariaDBConn != null) {
				mariaDBConn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@AfterClass //테스트의 갯수 상관없이 실행 후 딱 한번만 실행
	public static void tearDownClass() {
		System.out.println("테스트 종료");
	}

}
