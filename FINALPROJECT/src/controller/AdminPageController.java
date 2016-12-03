/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name: AdminPageController.java
 * Package Name: controllers
 * @author Jena Mehta A20382924
 * @version 0.1
 */
package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.CustomerDAO;
import dao.ProductDAO;
import dao.SubmitFeedbackDAO;
import model.GiveFeedback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdminPageController
{
	
	private Stage dialogStage;
	@FXML private Button newProductBtn;
	@FXML private TextField productId;
	@FXML private Button deleteProduct;
	@FXML private Label deletedLabel;
	@FXML private TextField customerId;
	@FXML private Button seeFeedback;
	@FXML private Label feedbackDesc;
	@FXML private Label feedbackRating;
	@FXML private Button logout;
	@FXML private TextField updateProductId;
	@FXML private TextField updatedPrice;
	@FXML private Button updatePriceBtn;
	@FXML private Label errorMsg;
	
	
	
	public void setDialogStage(Stage dialogStage) 
	{
		this.dialogStage = dialogStage;
	}
	/**
	 * Add new product option for Admin
	 * 
	 * Admin may add Multiple products
	 */
	public void AddNewProduct()
	{
		try 
		{
			Stage primaryStage = new Stage();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewProduct.fxml"));
			
            AnchorPane root = (AnchorPane) loader.load();
           
            primaryStage.setTitle("Add New Product Page");
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
            
            primaryStage.setScene(scene);
            
            NewProductController controller = loader.getController();
            
            controller.setDialogStage(primaryStage);
        
            
            
            primaryStage.show();
	        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}
		catch(Exception e)
		{
			System.out.println("Error!! Something Went Wrong" + e);
		}
	}
	
	public void DeleteProduct()
	{
		boolean validate = true;
		String id = this.productId.getText();
		
		if(id == "" || id.equals(""))
		{
			this.deletedLabel.setText("Need To enter Product ID");
			validate = false;	
		}
		for(int i=0; i<id.length(); i++)
		{
			if(Character.isLetter(id.charAt(i)))
			{
				this.deletedLabel.setText("Need To enter Product ID or number");
				validate = false;				
			}
			else
				this.deletedLabel.setText("");
		}
		if(validate)
		{
			int pid = Integer.parseInt(this.productId.getText());
			new CustomerDAO().DeleteProduct(pid);
			this.deletedLabel.setText("Deleted");
		}
	}
	
	public void GetRecentFeedback()
	{
		boolean validate = true;
		String id = this.customerId.getText();
		
		if(id == "" || id.equals(""))
		{
			this.deletedLabel.setText("Need To enter Product ID or number.");
			validate = false;							
		}
		for(int i=0; i<id.length(); i++)
		{
			if(Character.isLetter(id.charAt(i)))
			{
				this.deletedLabel.setText("Need To enter Product ID or number");
				validate = false;				
			}
			else
				this.deletedLabel.setText("");
		}
		if(validate)
		{
			int custId = Integer.parseInt(this.customerId.getText());
			GiveFeedback feedback = new SubmitFeedbackDAO().GetFeedback(custId);
			this.feedbackDesc.setText("Description : " + feedback.getDescription());
			this.feedbackRating.setText("Rating : " + feedback.getRating());
		}
	}
	
	public void LogOutAdmin()
	{
		CustomerDAO.CustomerIdentity = 0;
		try 
		{
			Stage primaryStage = new Stage();
			//Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
			//Inflate the view using the loader
            AnchorPane root = (AnchorPane) loader.load();
            //Set window title
            primaryStage.setTitle("Login Page");
            //Create a scene with the inflated view
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
            //Set the scene to the stage
            primaryStage.setScene(scene);
            //Get the controller instance from the loader
            LoginController controller = loader.getController();
            //Set the parent stage in the controller
            controller.setDialogStage(primaryStage);
            //Show the view
            primaryStage.show();
	        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}
		catch(Exception e)
		{
			System.out.println("Error!! Something Went Wrong!! " + e);
		}
	}
	
	public void UpdateProducts()
	{
		boolean validate = true;
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(this.updateProductId.getText());
		if(!matcher.matches())
		{
			this.errorMsg.setText("Need To enter Product ID.");
			validate = false;
		}
		matcher = pattern.matcher(this.updatedPrice.getText());
		if(!matcher.matches())
		{
			this.errorMsg.setText("Price is only allowed in Digits.");
			validate = false;
		}
		
		if(validate)
		{
			int id = Integer.parseInt(this.updateProductId.getText());
			int price = Integer.parseInt(this.updatedPrice.getText());
			new ProductDAO().UpdateProductPrice(errorMsg, id, price);
		}
	}
}
