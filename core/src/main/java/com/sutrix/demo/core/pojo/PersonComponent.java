package com.sutrix.demo.core.pojo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.adobe.cq.sightly.WCMUsePojo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sutrix.demo.core.bean.Person;
import com.sutrix.demo.core.utils.GetJson;

public class PersonComponent extends WCMUsePojo {

    private List<Person> list;

    @Override
    public void activate() throws Exception {
        String url = "http://www.mocky.io/v2/596f52020f00007d036b752e";

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        try {
//            URL oracle = new URL(url); // URL to Parse
//            URLConnection yc = oracle.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
//
//            String result = "";
//            String inputLine = "";
//            JSONArray a = new JSONArray();
//            while ((inputLine = in.readLine()) != null) {
//                result += inputLine;
//            }
//            JSONObject o = new JSONObject(result);
//            JSONObject data = o.getJSONObject("data");
//            // resp.getWriter().print(data.toString());
//            list = new ArrayList<Person>();
//            Person p = mapper.readValue(data.toString(), Person.class);
//            list.add(p);
//
//            in.close();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        
        
        list = GetJson.getData(url, null);
        

    }

    
    public List<Person> getList() {
        return list;
    }

    
    public void setList(List<Person> list) {
        this.list = list;
    }
    
    
}