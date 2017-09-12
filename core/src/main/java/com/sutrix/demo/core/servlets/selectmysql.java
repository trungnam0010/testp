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
@SlingServlet(paths = "/bin/selectmysql")
public class selectmysql extends SlingAllMethodsServlet {

	@Reference
	DataSourcePool pool;

	@Override
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException,
			IOException {
		try {
			DataSource datasourec = (DataSource) pool.getDataSource("data");
			Connection con = datasourec.getConnection();
			if (con != null) {

				// select

				String query = "select * from Employee";
				try {
					PreparedStatement statement = con.prepareStatement(query);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						resp.getWriter().println("ID: " + rs.getString("Id"));
						resp.getWriter().println("Name: " + rs.getString("Name"));
						resp.getWriter().println("Address: " + rs.getString("Address"));
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
				// insert

				/*String insert = "insert into Employee values (111,'aaa','hcm')";
				try {
					PreparedStatement statement = con.prepareStatement(insert);
					int i=statement.executeUpdate();
					if (i>0) {
						resp.getWriter().print("success");
					}else{
						resp.getWriter().print("gay");
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSourceNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
