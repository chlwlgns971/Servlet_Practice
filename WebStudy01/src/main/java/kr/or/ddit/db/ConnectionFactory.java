package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Design Pattern
 * 1. 생성패턴 : Singleton pattern, Factory Method pattern, Builder Pattern
 * 2. 구조패턴 : Adapter[Wrapper] Pattern, Proxy Pattern
 * 3. 행위패턴 : Template Method Pattern, Command Pattern, Strategy Pattern
 */
public class ConnectionFactory { //연결만을 담당하는 Factory
	private static String oracleURL;
	private static String oracleUser;
	private static String oraclePassword;
	static { //class를 로딩할 떄 한번만 실행하는 코드블럭
		Properties dbInfo = new Properties();
		
		try(
			InputStream is = ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/DBinfo.properties");	
		) {
			dbInfo.load(is);
			oracleURL = dbInfo.getProperty("url");
			oracleUser = dbInfo.getProperty("user");
			oraclePassword = dbInfo.getProperty("password");
			Class.forName(dbInfo.getProperty("driverClassName"));
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(e);
		}
	}


	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(oracleURL, oracleUser, oraclePassword);
	}
}
