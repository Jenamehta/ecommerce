
/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implEmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name: SUBMITFEEDBACKDAO.java
 * Package Name: DAO
 * @author Jena Mehta A20382924
 * @version 0.1
 */
package dao;

import model.GiveFeedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author Jena
 *
 */
public class SubmitFeedbackDAO 
{
	public void FeedbackFormDetails(int custId, String description, int rating)
	{
		String sql = "INSERT INTO A20382924_Feedback(CustomerId,Description,Rating) VALUES(?,?,?)";
		PreparedStatement statement;
		try
		{
			statement = DatabaseConnection.prepareStatement(sql);
			statement.setInt(1, custId);
			statement.setString(2, description);
			statement.setInt(3, rating);			
			statement.execute();
			System.out.println("Feedback Given Successfully.");
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param custId getCustomerid
	 * @return f
	 */
	public GiveFeedback GetFeedback(int custId)
	{
		GiveFeedback f = new GiveFeedback();
		String sql = "Select * from A20382924_feedback where CustomerId=?";
		PreparedStatement stmt;
		try 
		{
			stmt = DatabaseConnection.prepareStatement(sql);
			stmt.setInt(1, custId);
			ResultSet rst = stmt.executeQuery();
			if(rst.next())
			{
				f.setCustomerId(rst.getInt("CustomerId"));
				f.setDescription(rst.getString("Description"));
				f.setRating(rst.getInt("Rating"));
			}
			else
			{
				f.setDescription("Empty. Feedback Not Found for this user.");
				f.setRating(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return f;
	}
}