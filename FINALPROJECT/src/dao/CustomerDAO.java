

/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name: AddtoCartDAO.java
 * Package Name: DAO
 * @author Jena Mehta A20382924
 * @version 0.1
 */

package dao;

import model.Customer;
import model.Product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Label;
/**
 * 
 * @author Jena
 *
 */
public class CustomerDAO
{
	public static int CustomerIdentity = 0;
	public Customer Login(Customer newCustomer)
	{
		String sql = "Select * from A20382924_customer where username=? and password=?";
		PreparedStatement st;
		try
		{
			st = DatabaseConnection.prepareStatement(sql);
			st.setString(1, newCustomer.getUsername());
			st.setString(2, newCustomer.getPassword());
			ResultSet rs = st.executeQuery();
			if(	rs.next())
			{
				char admin = rs.getString("username").charAt(0);
				if(admin == 'Y')
				{
					//CustomerDAO.CustomerIdentity = rs.getInt("CustomerId");
					System.out.println("Logged in as admin successfully.");
				}
				else
				{
					newCustomer.productList = new ArrayList<Product>();
					CustomerDAO.CustomerIdentity = rs.getInt("CustomerId");
					System.out.println("Logged in as user successfully");
				}
			}
			else
			{
				System.out.println("No user found.");
			   // userLabel.setText("Username or password did not match.");
		        //passwordLabel.setText("Please try again..!!");
				return null;
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return newCustomer;
	}
	
	public Customer GetCustomerById(int custId)
	{
		Customer c = new Customer();
		String sql = "Select * from A20382924_customer where CustomerId=?";
		PreparedStatement st;
		try
		{
			st = DatabaseConnection.prepareStatement(sql);
			st.setInt(1, custId);
			ResultSet rs = st.executeQuery();
			if(	rs.next())
			{
				c.setCustomer_Id(rs.getInt("CustomerId"));
				c.setFirst_Name(rs.getString("FirstName"));
				c.setLast_Name(rs.getString("LastName"));
				c.setAge(rs.getInt("Age"));
				c.setGender(rs.getString("Gender"));
				c.setAddress(rs.getString("Address"));
				c.setEmail(rs.getString("Email"));
				c.setPhoneNumber(rs.getString("PhoneNo"));
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("password"));
			}
			else
			{
				System.out.println("No user found.");
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return c;
	}
	/**
	 * 
	 * @param newCustomer Customer
	 */
	public void NewRegistration(Customer newCustomer)
	{
		String sql = "INSERT INTO A20382924_customer(FirstName,LastName,Age,Gender,Address,Email,PhoneNo,username,password) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement;
		try
		{
			statement = DatabaseConnection.prepareStatement(sql);
			statement.setString(1, newCustomer.getFirst_Name());
			statement.setString(2, newCustomer.getLast_Name());
			statement.setInt(3, newCustomer.getAge());
			statement.setString(4, newCustomer.getGender());
			statement.setString(5, newCustomer.getAddress());
			statement.setString(6, newCustomer.getEmail());
			statement.setString(7, newCustomer.getPhoneNumber());
			statement.setString(8, newCustomer.getUsername());
			statement.setString(9, newCustomer.getPassword());
			
			statement.execute();
			System.out.println("User registered successfully");
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param pid Productid
	 * @param pname ProductName
	 * @param mfg Date
	 * @param cat Category
	 * @param price Price
	 * @param disc Discount
	 * @param total TotalQty
	 * @param available AvalibleQty
	 */
	public void AddProduct(int pid,String pname,Date mfg,char cat,int price,int disc,int total,int available)
	{
		String sql = "INSERT INTO A20382924_products(ProductId,ProductName,MfgDate,Category,Price,Discount,TotalQty,AvaliableQty) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement statement;
		try
		{
			statement = DatabaseConnection.prepareStatement(sql);
			statement.setInt(1, pid);
			statement.setString(2, pname);
			statement.setDate(3, mfg);
			statement.setString(4, String.valueOf(cat));
			statement.setInt(5, price);
			statement.setInt(6, disc);
			statement.setInt(7, total);
			statement.setInt(8, available);
			
			statement.execute();
			System.out.println("Product Added successfully");
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param pid ProductID
	 */
	public void DeleteProduct(int pid)
	{
		try
		{
			String delete = "Delete from A20382924_products where ProductId=?";
			PreparedStatement deleteStmt = DatabaseConnection.prepareStatement(delete);
			deleteStmt.setInt(1, pid);
			deleteStmt.execute();
			System.out.println("Product deleted from table..");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param newPassword NEWPASSWORD
	 * @param identity GETUSERNAME
	 */
	public void UpdateAccount(String newPassword, int identity)
	{
		try
		{
			String update = "UPDATE A20382924_customer set password=? where CustomerId=?";
			PreparedStatement pst = DatabaseConnection.prepareStatement(update);
			pst.setString(1, newPassword);
			pst.setInt(2, identity);
			pst.executeUpdate();
			System.out.println("Password updated successfully.");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}