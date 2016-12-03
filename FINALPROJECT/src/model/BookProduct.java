package model;

import java.sql.Date;

public class BookProduct extends Product 
{
	private String author;

	public BookProduct()
	{
		super();
		this.author = "";
	}
	
	public BookProduct(int id,String author)
	{
		this.setProduct_Id(id);
		this.author = author;
	}
	
	public BookProduct(int productId, String product_Name, Date manufacture_Date, char category, int price, int discount, int total_quantity,	int available_quantity, String author) 
	{
		super(productId, product_Name, manufacture_Date, category, price, discount, total_quantity, available_quantity);
		this.author = author;
	}

	public String getAuthor()
	{
		return this.author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}

}
