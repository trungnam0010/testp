package com.sutrix.demo.core.Controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sutrix.demo.core.models.Product;



public class ProductController{
	
	public static List<Product> readFileCSV(BufferedReader br)
	{
		List<Product> products = null;
		String csvSplit = ",";
		String line = "";
		try {
		     if(br != null)
		     {
		    	 products = new ArrayList<Product>();
		    	 line = br.readLine();
		    	 while ((line = br.readLine()) != null) {
			         String[] country = line.split(csvSplit);
			         products.add(new Product(country[0], country[1], country[2]));
			     }
		    	 
		     }
		
		 }catch (FileNotFoundException ex) {
		     ex.printStackTrace();
		 }catch (IOException ex) {
		     ex.printStackTrace();
		 }finally {
		     if (br != null) {
		         try {
		             br.close();
		         } catch (IOException e) {
		             e.printStackTrace();
		         }
		     }
		 }
		return products;
	}
}