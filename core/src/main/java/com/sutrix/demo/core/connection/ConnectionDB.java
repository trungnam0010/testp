package com.sutrix.demo.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB  {
	public static Connection getConnection() {
		Connection con = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://10.0.1.45:3306/sutrix_aem_training", "sutrix", "sutrix@");
			if (con != null) {
				//System.out.println("connect sucessed!");
				return con;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static void main(String[] args) {
		ConnectionDB.getConnection();
	}
	
}