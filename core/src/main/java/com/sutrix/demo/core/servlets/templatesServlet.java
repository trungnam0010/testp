package com.sutrix.demo.core.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.adobe.cq.commerce.common.ValueMapDecorator;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;

@SuppressWarnings("serial")
@SlingServlet(paths = "/bin/templates", methods = "GET", metatype = false)
public class templatesServlet extends SlingAllMethodsServlet {
	@Reference
	DataSourcePool pool;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		try {
			DataSource datasourec = (DataSource) pool.getDataSource("data");
			Connection con = datasourec.getConnection();
			if (con != null) {
				PreparedStatement pre = con
						.prepareStatement("select*from Employee");
				ResultSet rs = pre.executeQuery();
				List<Resource> rsList = new ArrayList<Resource>();
				ValueMap vm = null;
				while (rs.next()) {
					vm = new ValueMapDecorator(new HashMap<String, Object>());
					vm.put("value", rs.getString("Name"));
					vm.put("text", rs.getString("Name"));
					rsList.add(new ValueMapResource(request
							.getResourceResolver(), new ResourceMetadata(),
							"nt:unstructured", vm));
				}
				com.adobe.granite.ui.components.ds.DataSource ctr = new SimpleDataSource(
						rsList.iterator());
				request.setAttribute(
						com.adobe.granite.ui.components.ds.DataSource.class
								.getName(), ctr);
			}
		} catch (DataSourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}