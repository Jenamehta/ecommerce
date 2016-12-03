
/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name: GiveFeedBackController.java
 * Package Name: controllers
 * @author Jena Mehta A20382924
 * @version 0.1
 */
package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dao.CustomerDAO;
import model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RegistrationController 
{
	//This is the parent stage
	private Stage dialogStage;
		@FXML private TextField input_first_name;
		@FXML private TextField input_last_name;		
		@FXML private TextField input_age;
		@FXML private RadioButton radioMale;
	@FXML private RadioButton radioFemale;
		@FXML private TextField input_address;
		@FXML private TextField input_email;
		@FXML private TextField input_phone;
		@FXML private TextField input_username;
		@FXML private PasswordField input_password;
	@FXML private Label validFname;
	@FXML private Label validLname;
	@FXML private Label validAge;
	@FXML private Label validAddress;
	@FXML private Label validEmail;
	@FXML private Label validPhone;
	@FXML private Label validUsername;
	@FXML private Label validPassword;
		public void setDialogStage(Stage dialogStage) 
	{
		this.dialogStage = dialogStage;
	}
	
	public void NewRegistration()
	{
		boolean validated = true;
		final ToggleGroup group = new ToggleGroup();
		radioMale.setToggleGroup(group);
		radioMale.setSelected(true);
		radioFemale.setToggleGroup(group);
		radioFemale.setUserData("Female");
		radioMale.setUserData("Male");
		String fname = input_first_name.getText();
		String lname = input_last_name.getText();
		String age = input_age.getText();
		String gender = group.getSelectedToggle().getUserData().toString();
		String address = input_address.getText();
		String email = input_email.getText();
		String phone = input_phone.getText();
		String username = input_username.getText();
		String password = input_password.getText();

				if(fname.equals(""))
		{
			validFname.setText("No UserName");
			validated = false;
		}
		else
			validFname.setText("");		
		
		if(lname.equals(""))
		{
			validLname.setText("Enter Username");
			validated = false;
		}
		else
			validLname.setText("");
		
		if(age.equals(""))
		{
			validAge.setText("Enter Correct Age");
			validated = false;
		}
		else
			validAge.setText("");
		
		for(int i=0; i<age.length(); i++)
		{
			if(Character.isLetter(age.charAt(i)))
			{
				validAge.setText("Only numbers please");
				validated = false;
			}
			else
				validAge.setText("");
		}

		if(address.equals(""))
		{
			validAddress.setText("We wont ship products to blnk address!!");
			validated = false;
		}
		else
			validAddress.setText("");

		if(email.equals(""))
		{
			validEmail.setText("Need to Enter email");
			validated = false;
		}
		else
			validEmail.setText("");

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher matcher = emailPattern.matcher(email);
		if(!matcher.matches())
		{
			validEmail.setText("Enter proper email, This is Invalid");
			validated = false;
		}
		else
			validEmail.setText("");

		
		if(phone.equals(""))
		{
			validPhone.setText("Enter phone Number");
			validated = false;
		}
		else
			validPhone.setText("");
		
		for(int i=0; i<phone.length(); i++)
		{
			if(Character.isLetter(phone.charAt(i)))
			{
				validPhone.setText("Dont have letters.");
				validated = false;
			}
			else
				validPhone.setText("");
		}

		if(username.equals(""))
		{
			validUsername.setText("Username Blank");
			validated = false;
		}
		else
			validUsername.setText("");
		
		if(password.equals(""))
		{
			validPassword.setText("Password not entered");
			validated = false;
		}
		else
			validPassword.setText("");
		
		if(validated)
		{
			Customer regCustomer = new Customer();
			regCustomer.setFirst_Name(fname);
			regCustomer.setLast_Name(lname);
			regCustomer.setAge(Integer.parseInt(age));
			regCustomer.setGender(gender);
			regCustomer.setAddress(address);
			regCustomer.setEmail(email);
			regCustomer.setPhoneNumber(phone);
			regCustomer.setUsername(username);
			regCustomer.setPassword(password);
			CustomerDAO d = new CustomerDAO();
			d.NewRegistration(regCustomer);
			
			dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}
	}
}