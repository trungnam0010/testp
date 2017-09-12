package com.sutrix.demo.core.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;

@SuppressWarnings("serial")
@SlingServlet(paths = "/bin/adap")
public class testadaptive extends SlingAllMethodsServlet {

	@Reference
	DataSourcePool pool;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		try {
			DataSource datasourec = (DataSource) pool.getDataSource("data");
			Connection con = datasourec.getConnection();		
			String id= request.getParameter("id");
			String name= request.getParameter("name");
			String address=request.getParameter("address");
			response.getWriter().print(id+name);
			PreparedStatement pre = con.prepareStatement("insert into Employee values (?, ?, ?)");
			pre.setInt(1, Integer.parseInt(id));
			pre.setString(1, name);
			pre.setString(2, address);
			pre.executeUpdate();
		} catch (SQLException e) {
			response.getWriter().println(e.toString());
		} catch (DataSourceNotFoundException e) {
			response.getWriter().println(e.toString());
		}
	}
}
