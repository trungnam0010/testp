package com.sutrix.demo.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sutrix.demo.core.bean.Person;

public class GetJson {

    public static List<Person> getData(String url, String body) {
        List<Person> list = new ArrayList<Person>();
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            // StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            // request.setEntity(params);
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");

            Gson gson = new Gson();
            JsonObject jo = gson.fromJson(json, JsonObject.class);
            JsonObject data = jo.getAsJsonObject("data");
            Person p = gson.fromJson(data, Person.class);
            list.add(p);
            return list;

        }
        catch (IOException ex) {}
        return null;
    }
}