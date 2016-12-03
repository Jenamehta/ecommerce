
/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implEmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name: OrderDetailsDAO.java
 * Package Name: DAO
 * @author Jena Mehta A20382924
 * @version 0.1
 */
package dao;

import model.Basket;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

public class OrderDetailsDAO 
{
	public Basket GetBillAmount(int id)
	{
		Basket b = new Basket();
		b.setCustomerId(CustomerDAO.CustomerIdentity);
		int totalBill = 0;
		String sql = "Select * from A20382924_Cart where CustomerId=?";
		PreparedStatement st;
		try
		{
			st = DatabaseConnection.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				totalBill = totalBill + rs.getInt("PicePerUnit");
			}
			b.setTotalBillAmount(totalBill);
			Date shoppingDate = Date.valueOf(LocalDate.now());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 7);
			shoppingDate = new Date(cal.getTime().getTime());
			b.setDeliveryDate(shoppingDate);
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * Update order table and calculate totalbill with delivery
	 * 
	 * @param b details
	 */
	public void AddOrderDetailsInfo(Basket b)
	{
		String sql = "INSERT INTO A20382924_Orders(CustomerId,TotalBillAmount,DeliveryDate) VALUES(?,?,?)";
		PreparedStatement statement;
		try
		{
			statement = DatabaseConnection.prepareStatement(sql);
			statement.setInt(1, b.getCustomerId());
			statement.setInt(2, b.getTotalBillAmount());
			statement.setDate(3, b.getDeliveryDate());
			statement.execute();
			System.out.println("Hello!! YOUR ORDER IS PLACED");
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}