package model;

import java.sql.Date;

public class ElectronicsProduct extends Product
{
	private int wattPower;
	
	public ElectronicsProduct()
	{
		super();
		this.wattPower = 0;
	}
	
	public ElectronicsProduct(int id, int watt)
	{
		this.setProduct_Id(id);
		this.wattPower = watt;
	}
	
	public ElectronicsProduct(int productId, String product_Name, Date manufacture_Date, char category, int price, int discount, int total_quantity, int available_quantity, int wattPower) 
	{
		super(productId, product_Name, manufacture_Date, category, price, discount, total_quantity, available_quantity);
		this.wattPower = wattPower;
	}


	public int getWattPower()
	{
		return this.wattPower;
	}
	
	public void setWattPower(int watt)
	{
		this.wattPower = watt;
	}
}
