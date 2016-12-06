package com.xinguan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.jdbc.PreparedStatement;

public class JdbcUtils {
	
	private static Properties config = new Properties();
	static{
		try {
			config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			Class.forName(config.getProperty("driver"));
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static com.mysql.jdbc.Connection getConnection() throws SQLException{
		return (com.mysql.jdbc.Connection) DriverManager.getConnection(config.getProperty("url"), config.getProperty("username"), config.getProperty("password"));
	}
	
	
	public static void release(Connection conn,java.sql.PreparedStatement stmt,ResultSet rs){
		
		if(rs!=null){
			try{
				rs.close();   //throw new 
			}catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(stmt!=null){
			try{
				stmt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn!=null){
			try{
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
}	
