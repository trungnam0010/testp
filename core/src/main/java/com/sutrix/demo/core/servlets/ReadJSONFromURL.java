package com.sutrix.demo.core.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sutrix.demo.core.bean.Person;

/**
 * Servlet that writes some sample content into the response. It is mounted for all resources of a specific Sling
 * resource type. The {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are idempotent. For write
 * operations use the {@link SlingAllMethodsServlet}.
 */
@SuppressWarnings("serial")
@SlingServlet(resourceTypes = "demo/components/structure/page", selectors = "ss", extensions = "html", methods = "GET", metatype = true)
public class ReadJSONFromURL extends SlingSafeMethodsServlet {

    private List<Person> persons = new ArrayList<Person>();

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {

        String url = "http://www.mocky.io/v2/596f52020f00007d036b752e";
        List<Person> list = new ArrayList<Person>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            URL oracle = new URL(url); // URL to Parse
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String result = "";
            String inputLine = "";
            JSONArray a = new JSONArray();
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
            }
            JSONObject o = new JSONObject(result);
            JSONObject data = o.getJSONObject("data");
            resp.getWriter().print(data.toString());
            list = new ArrayList<Person>();
            Person p = mapper.readValue(data.toString(), Person.class);
            list.add(p);

            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}