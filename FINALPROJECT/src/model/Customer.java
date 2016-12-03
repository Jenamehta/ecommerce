package model;

import dao.CustomerDAO;

import java.util.ArrayList;
import java.util.List;

interface EditCustomer 
{
	void EditCustomerDetails(String pwd, int id);
}

public class Customer implements EditCustomer
{
	private int customer_Id;
	private String first_Name;
	private String last_Name;
	private int age;
	private String gender;
	private String address;
	private String email;
	private String phoneNumber;
	private String username;
	private String password;
	private String isAdmin;
	public static List<Product> productList;

	public Customer()
	{
		this.first_Name = "";
		this.last_Name = "";
		this.age = 20;
		this.gender = "Male";
		this.address = "";
		this.email = "";
		this.phoneNumber = "02028492264";
		productList = new ArrayList<Product>();
	}
	
	public Customer(String username, String password)
	{
		try
		{
			setUsername(username);
			setPassword(password);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public Customer(String fname, String lname, int age, String gender, String address, String email, String phone, String username, String password)
	{
		setFirst_Name(fname);
		setLast_Name(lname);
		setAge(age);
		setGender(gender);
		setAddress(address);
		setEmail(email);
		setPhoneNumber(phone);
		setUsername(username);
		setPassword(password);
		productList = new ArrayList<Product>();
	}
	
	public String getFirst_Name()
	{
		return this.first_Name;
	}
	
	public String getLast_Name()
	{
		return this.last_Name;
	}

	public int getAge() 
	{
		return this.age;
	}
	
	public String getGender() 
	{
		return this.gender;
	}
	
	public String getAddress() 
	{
		return this.address;
	}
	
	public String getEmail() 
	{
		return this.email;
	}
	
	public String getPhoneNumber() 
	{
		return this.phoneNumber;
	}

	public List<Product> getProductList() 
	{
		return this.productList;
	}

	public int getCustomer_Id() 
	{
		return this.customer_Id;
	}

	public String getUsername() 
	{
		return username;
	}

	public String getPassword() 
	{
		return password;
	}
	public String getIsAdmin() 
	{
		return isAdmin;
	}
	
	public void setCustomer_Id(int customer_Id)
	{
		this.customer_Id = customer_Id;
	}

	public void setFirst_Name(String first_Name)
	{
		this.first_Name = first_Name;
	}
	public void setIsAdmin(String isAdmin)
	{
		this.isAdmin = isAdmin;
	}
		
	public void setLast_Name(String last_Name)
	{
		this.last_Name = last_Name;
	}
	
	public void setAge(int age) 
	{
		this.age = age;
	}
	
	public void setGender(String gender) 
	{
		this.gender = gender;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	
	public void setProductList(List<Product> productList) 
	{
		this.productList = productList;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public void EditCustomerDetails(String pwd, int id) 
	{
	 	new CustomerDAO().UpdateAccount(pwd, id);
	}
}