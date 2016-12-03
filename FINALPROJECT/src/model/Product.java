package model;

import java.sql.Date;

public class Product 
{
	private int product_Id;
	private String product_Name;
	private Date manufacture_Date;
	private char category;
	private int price;
	private int discount;
	private int total_quantity;
	private int available_quantity;

	public Product() 
	{
		this.product_Id = 1;
		this.product_Name = "";
		this.manufacture_Date = Date.valueOf("2015-11-20");
		this.category = 'E';
		this.price = 0;
		this.discount = 0;
		this.total_quantity = 0;
		this.available_quantity = 0;
	}
	
	public Product(int id, String product_Name, Date manufacture_Date, char category, int price, int discount, int total_quantity, int available_quantity) 
	{
		this.product_Name = product_Name;
		this.manufacture_Date = manufacture_Date;
		this.category = category;
		this.price = price;
		this.discount = discount;
		this.total_quantity = total_quantity;
		this.available_quantity = available_quantity;
	}

	public Product(String productName, int price, int discount, int totalQty)
	{
		this.product_Name = productName;
		this.price = price;
		this.discount = discount;
		this.total_quantity = totalQty;
	}
	
	public int getProduct_Id()
	{
		return this.product_Id;
	}
	
	public String getProduct_Name() 
	{
		return this.product_Name;
	}
	
	public Date getManufacture_Date() 
	{
		return this.manufacture_Date;
	}
	
	public char getCategory() 
	{
		return this.category;
	}
	
	public int getPrice() 
	{
		return this.price;
	}
	
	public int getDiscount() 
	{
		return this.discount;
	}
	
	public int getTotal_quantity() 
	{
		return this.total_quantity;
	}
	
	public int getAvailable_quantity() 
	{
		return this.available_quantity;
	}

	public void setProduct_Id(int product_Id)
	{
		this.product_Id = product_Id;
	}

	public void setProduct_Name(String product_Name)
	{
		this.product_Name = product_Name;
	}

	public void setManufacture_Date(Date manufacture_Date)
	{
		this.manufacture_Date = manufacture_Date;
	}

	public void setCategory(char category)
	{
		this.category = category;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}

	public void setDiscount(int discount) 
	{
		this.discount = discount;
	}

	public void setTotal_quantity(int total_quantity) 
	{
		this.total_quantity = total_quantity;
	}

	public void setAvailable_quantity(int available_quantity) 
	{
		this.available_quantity = available_quantity;
	}
}
