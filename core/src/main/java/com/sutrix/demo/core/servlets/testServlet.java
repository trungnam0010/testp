package com.sutrix.demo.core.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

@SuppressWarnings("serial")
@SlingServlet(paths = "/bin/d2")
public class testServlet extends SlingAllMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String line = "";
		
		BufferedReader br = null;

		Session session;
			
		session = request.getResourceResolver().adaptTo(Session.class);
	
		

		// Create a node that represents the root node
		try {
			Node etc = session.getNode("/etc");
			
			if(null != etc) {
					
				int i= 1;
				if(etc.hasNode("demo")) {
					Node persons = etc.getNode("demo").addNode("list");
					br = new BufferedReader(new FileReader("/media/DATA/temps/demo/core/libs/Data.csv"));
					br.readLine();
					while ((line = br.readLine()) != null) {
						String[] txts = line.split(",");
						persons.getPath();
						Node person = persons.addNode("mydataperson"+i, "nt:unstructured");
						person.setProperty("id", txts[0]);
						person.setProperty("firstName", txts[1]);
						person.setProperty("lastname", txts[2]);
						person.setProperty("mail", txts[3]);
						person.setProperty("gender", txts[4]);
						person.setProperty("Ipaddress", txts[5]);
						
						i +=1;
					}
					
					session.save();
					br.close();
					
				}else {
					Node demo = etc.addNode("demo");
					Node persons = demo.addNode("mypersonlist");
					br = new BufferedReader(new FileReader(new File("/media/DATA/temps/demo/core/libs/Data.csv")));
					br.readLine();
					while ((line = br.readLine()) != null) {
						String[] txts = line.split(",");
						Node person = persons.addNode("mydataperson"+i,"nt:unstructured");
						person.setProperty("id", txts[0]);
						person.setProperty("firstName", txts[1]);
						person.setProperty("lastname", txts[2]);
						person.setProperty("mail", txts[3]);
						person.setProperty("gender", txts[4]);
						person.setProperty("Ipaddress", txts[5]);
						i +=1;
					}
					session.save();
					br.close();
				}
				
			}						

			session.logout();
		} catch (Exception e1) {
			// TODO Auto-generated catch block

			response.getWriter().println("error-" + e1.toString() + "-" + e1.getCause());
		}
	}

}