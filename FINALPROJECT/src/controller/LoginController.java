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

import java.io.IOException;
import dao.CustomerDAO;
import model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import controller.AddToCartController;

public class LoginController
{

	private Stage dialogStage;

	@FXML private TextField uname;
	@FXML private PasswordField pwd;
	@FXML private Label userLabel;
	@FXML private Label passwordLabel;
	@FXML private Button loginBtn;
	@FXML private Button registerBtn;
	

	public void setDialogStage(Stage dialogStage) 
	{
		this.dialogStage = dialogStage;
	}

	public void LoginCustomer() throws IOException
	{
		String user = this.uname.getText();
		String password = this.pwd.getText();
		
		if(user == null || user.trim().equals("")) 
		{
			this.userLabel.setText("Type Username");
		}
		if(password == null || password.trim().equals("")) 
		{
			this.passwordLabel.setText("Type password");
		}
		
		Customer loginCustomer = new Customer();
		loginCustomer.setUsername(user);
		loginCustomer.setPassword(password);
		CustomerDAO d = new CustomerDAO();
		loginCustomer = d.Login(loginCustomer);
		if(loginCustomer != null && !(loginCustomer.getUsername().equals("admin")))
			showProducts(loginCustomer);
		else if(loginCustomer != null && loginCustomer.getUsername().equals("admin"))
			this.GoToAdminPage();
	}
	
	
	public void showProducts(Customer loginCustomer)
	{
		try 
		{
			Stage primaryStage = new Stage();
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddToCart.fxml"));
			
            AnchorPane root = (AnchorPane) loader.load();
            
            primaryStage.setTitle("Add Cart");
          
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/application1.css").toExternalForm());
         
            primaryStage.setScene(scene);
        
            AddToCartController controller = loader.getController();
           
            controller.setDialogStage(primaryStage);
	  
	        primaryStage.show();
	        controller.GetAllProductsList(loginCustomer);
	        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}
		catch(Exception e)
		{
			System.out.println("Error" + e);
		}
	}
	
	public void RegisterCustomer()
	{
		try 
		{
			Stage primaryStage = new Stage();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RegisterCustomer.fxml"));
			
            AnchorPane root = (AnchorPane) loader.load();
           
            primaryStage.setTitle("Registration Page");
  
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
           
            primaryStage.setScene(scene);
         
            RegistrationController controller = loader.getController();
           
            controller.setDialogStage(primaryStage);
            
            primaryStage.show();
		}
		catch(Exception e)
		{
			System.out.println("Error!! " + e);
		}
	}
	
	public void GoToAdminPage() throws IOException
	{
		try 
		{
			Stage primaryStage = new Stage();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminPage.fxml"));
			
            AnchorPane root = (AnchorPane) loader.load();
      
            primaryStage.setTitle("Administrator Page");
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
          
            primaryStage.setScene(scene);
         
            AdminPageController controller = loader.getController();
            
            controller.setDialogStage(primaryStage);
           
            primaryStage.show();
	        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}
		catch(Exception e)
		{
			System.out.println("Error!! Something went Wrong!! " + e);
		}
	}
}
