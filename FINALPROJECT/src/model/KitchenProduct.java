package model;

import java.sql.Date;

public class KitchenProduct extends Product 
{
	private String brand;
	
	public KitchenProduct()
	{
		super();
		this.brand = "";
	}
	
	public KitchenProduct(int pid, String brand)
	{
		this.setProduct_Id(pid);
		this.brand = brand;
	}	
	
	public KitchenProduct(int productId, String product_Name, Date manufacture_Date, char category, int price, int discount, int total_quantity, int available_quantity, String description) 
	{
		super(productId, product_Name, manufacture_Date, category, price, discount, total_quantity, available_quantity);
		this.brand = description;
	}

	public String getBrand()
	{
		return this.brand;
	}
	
	public void setBrand(String brand)
	{
		this.brand = brand;
	}
}
