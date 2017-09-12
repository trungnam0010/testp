package com.sutrix.demo.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;

import org.apache.commons.mail.HtmlEmail;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

@SuppressWarnings("serial")
@SlingServlet(paths = "/bin/d1")
public class htmlemail extends SlingSafeMethodsServlet {
	@Reference
	private MessageGatewayService messageGatewayService;
	private MessageGateway<HtmlEmail> messageGateway;

	@Override
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException,
			IOException {
		HtmlEmail htmEmail = new HtmlEmail();
		List<InternetAddress> lstMailToIntAddress = new ArrayList<InternetAddress>();
		try {
			lstMailToIntAddress.add(new InternetAddress("kuyoung111@gmail.com"));
			htmEmail.setTo(lstMailToIntAddress);
			htmEmail.setSubject("Hello");
			htmEmail.setHtmlMsg("today is monday");
			this.messageGateway = this.messageGatewayService.getGateway(HtmlEmail.class);
			if (null != this.messageGateway) {
				this.messageGateway.send(htmEmail);
			}
			resp.getWriter().print("success");
		} catch (Exception e) {
			System.out.print("Loi :" + e.getMessage());
			e.printStackTrace();
			resp.getWriter().print("Loi:" + e);
		}	
	}
}