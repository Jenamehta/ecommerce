package model;

import java.sql.Date;

public class Basket
{
	private int OrderId;
	private int CustomerId;
	private int TotalBillAmount;
	private Date DeliveryDate;

	public Basket()
	{
		this.OrderId = 0;
		this.CustomerId = 0;
		this.TotalBillAmount = 0;
		this.DeliveryDate = Date.valueOf("2015-11-11");
	}
	
	public Basket(int orderId, int customerId, int totalBillAmount, Date deliveryDate)
	{
		this.OrderId = orderId;
		this.CustomerId = customerId;
		this.TotalBillAmount = totalBillAmount;
		this.DeliveryDate = deliveryDate;
	}

	
	public int getOrderId() 
	{
		return OrderId;
	}

	public int getCustomerId() 
	{
		return CustomerId;
	}

	public int getTotalBillAmount() 
	{
		return TotalBillAmount;
	}

	public Date getDeliveryDate() 
	{
		return DeliveryDate;
	}

	public void setOrderId(int orderId)
	{
		OrderId = orderId;
	}

	public void setCustomerId(int customerId) 
	{
		CustomerId = customerId;
	}

	public void setTotalBillAmount(int totalBillAmount) 
	{
		TotalBillAmount = totalBillAmount;
	}

	public void setDeliveryDate(Date deliveryDate) 
	{
		DeliveryDate = deliveryDate;
	}
}
