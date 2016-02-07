package com.kenscio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public static void insert(String name, int age, int sal)
	{
		final String querry = "INSERT INTO TEST(NAME,AGE,SAL) VALUES('"+name+"',"+age+","+sal+");";
		System.out.println(querry);
		try {
			Connection con = DBConnect.getConnection();
			Statement smt = con.createStatement();
			int rs = smt.executeUpdate(querry);
			if(rs>0)
			{
				System.out.println("querry executed successfully");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
