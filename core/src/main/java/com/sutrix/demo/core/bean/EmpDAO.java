package com.sutrix.demo.core.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sutrix.demo.core.Entityy.EmpDTO;


public class EmpDAO {
	public boolean insert(Connection con, EmpDTO dto) {
		boolean check = false;
		try {

			String insert = "insert into Employee values (?,?,?)";
			PreparedStatement statement = con.prepareStatement(insert);
			statement.setInt(1, dto.getID());
			statement.setString(2, dto.getName());
			statement.setString(3, dto.getAddress());
			statement.executeQuery();
			check=true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return check;
	}
}