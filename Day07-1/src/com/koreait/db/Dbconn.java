package com.koreait.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconn {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/jspstudy";
			String uid = "root";
			String upw = "enenflfk0012!9";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, uid, upw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
