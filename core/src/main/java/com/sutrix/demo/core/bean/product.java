package com.sutrix.demo.core.bean;

public class product {
	
	private String productid;
	private String priductname;
	private String price;
	
	
	public product(String productid, String priductname, String price) {
		this.productid = productid;
		this.priductname = priductname;
		this.price = price;
	}
	
	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getPriductname() {
		return priductname;
	}
	public void setPriductname(String priductname) {
		this.priductname = priductname;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "[productid=" + productid + ", priductname=" + priductname + ", price=" + price + "]<br>";
	}

}