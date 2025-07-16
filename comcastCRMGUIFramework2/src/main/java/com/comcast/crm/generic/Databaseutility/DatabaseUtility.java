package com.comcast.crm.generic.Databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
         Connection conn;
	public void getConnection(String url, String username, String password) {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url,username,password);
		}catch(Exception e) {
			
		}	
	}
	
	public void closeDbconnection() {
		try {
			conn.close();
		}catch(Exception e) {
			
		}
		
	}
	
	public ResultSet executeSelectQuery(String Query) {
		ResultSet result =null;
		try {
		Statement stat = conn.createStatement();
		result = stat.executeQuery(Query);
		}catch(Exception e) {
			
		}
		return result;	
	}
	
	public int executeNonSelectQuery(String Query) {
		int result = 0;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(Query);
	
		}catch(Exception e) {
			
		}
		return result;
		
		
		
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
