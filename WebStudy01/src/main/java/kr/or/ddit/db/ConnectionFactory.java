package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

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
	private static DataSource dataSource;
	
	static { //class를 로딩할 떄 한번만 실행하는 코드블럭
		Properties dbInfo = new Properties();
		
		try(
			InputStream is = ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/DBinfo.properties");	
		) {
			dbInfo.load(is);
			oracleURL = dbInfo.getProperty("url");
			oracleUser = dbInfo.getProperty("user");
			oraclePassword = dbInfo.getProperty("password");
			//Class.forName(dbInfo.getProperty("driverClassName"));
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(dbInfo.getProperty("driverClassName"));
			bds.setUrl(oracleURL);
			bds.setUsername(oracleUser);
			bds.setPassword(oraclePassword);
			
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initialSize"))); // 미리 5개를 생성
			bds.setMaxWaitMillis(Integer.parseInt(dbInfo.getProperty("maxWait"))); //5개를 다 쓰면 반납이 될때까지 2초간 대기 후 반납이 안되면 1개 더 생성 -> 반복해서 setMaxTotal 수까지 생성한다.
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal")));
			bds.setMaxIdle(Integer.parseInt(dbInfo.getProperty("maxIdle"))); // 최대 5개의 connection은 놀고 있을 수 있음. (초과하는건 삭제)
			dataSource = bds;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	public static Connection getConnection() throws SQLException {
		//return DriverManager.getConnection(oracleURL, oracleUser, oraclePassword);
		return dataSource.getConnection();
	}
}
