package model;

public class GiveFeedback 
{
	private int CustomerId;
	private String description;
	private int rating;
	
	public GiveFeedback()
	{
		this.CustomerId = 0;
		this.description = "";
		this.rating = 1;
	}
	
	public GiveFeedback(int customerId, String description, int rating) 
	{
		this.CustomerId = customerId;
		this.description = description;
		this.rating = rating;
	}

	
	public int getCustomerId()
	{
		return CustomerId;
	}

	public String getDescription() 
	{
		return this.description;
	}
	
	public int getRating() 
	{
		return this.rating;
	}

	public void setCustomerId(int customerId)
	{
		this.CustomerId = customerId;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setRating(int rating) 
	{
		this.rating = rating;
	}
}