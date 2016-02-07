package com.kenscio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	static Connection con = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver loaded....");
		con = DriverManager.getConnection("jdbc:mysql://ds.kenscio.com:3306/LoginProject","vikas","pass123");
		System.out.println("connected....");
		return con;
		

	}

}
