/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name:Controller.java
 * Package Name: controllers
 * @author Jena Mehta A20382924
 * @version 0.1
 */
package controller;

import dao.CustomerDAO;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewProductController 
{
//Stage 
	private Stage dialogStage;
	@FXML private TextField id;
	@FXML private TextField name;
	@FXML private DatePicker datePicker;
	@FXML private TextField category;
	@FXML private TextField price;
	@FXML private TextField discount;
	@FXML private TextField totalQuantity;
	@FXML private TextField availableQuantity;
	@FXML private Label validProductId;
	@FXML private Label validProductName;
	@FXML private Label validCategory;
	@FXML private Label validPrice;
	@FXML private Label validDiscount;
	@FXML private Label validTotalQuantity;
	@FXML private Label validAvailableQuantity;
	@FXML private Label validDate;
	//set current view
	public void setDialogStage(Stage dialogStage) 
	{
		this.dialogStage = dialogStage;
	}
	/**
	 * To Add product Insserting new value
	 * @throws IOException e
	 */
	public void AddProductToDatabase() throws IOException
	{
		try
		{
			boolean validated = true;
			String pid = this.id.getText();
			String pname = this.name.getText();
			Date mfg = Date.valueOf(this.datePicker.getValue());
			String cat = this.category.getText();
			String price = this.price.getText();
			String disc = this.discount.getText();
			String total = this.totalQuantity.getText();
			String available = this.availableQuantity.getText();
			
			//validations
			if(pid.equals(""))
			{
				validProductId.setText("No product");
				this.validDate.setText("");
				validated = false;
			}
			else
			{
				validProductId.setText("");
				this.validDate.setText("");
			}
			for(int i=0; i<pid.length(); i++)
			{
				if(Character.isLetter(pid.charAt(i)))
				{
					validProductId.setText("Need to use Numbers");
					validated = false;
				}
				else
					validProductId.setText("");
			}
			
			if(cat.equals(""))
			{
				validCategory.setText("No Category");
				validated = false;
			}
			else
				validCategory.setText("");	
			
			for(int i=0; i<cat.length(); i++)
			{
				if(Character.isDigit(cat.charAt(i)))
				{
					validCategory.setText("Need to enter Characters");
					validated = false;
				}
				else
					validCategory.setText("");
			}
							
			if(pname.equals(""))
			{
				validProductName.setText("No NAme");
				validated = false;
			}
			else
				validProductName.setText("");	
	
			if(price.equals(""))
			{
				validPrice.setText("Price not set");
				validated = false;
			}
			else
				validPrice.setText("");	
	
			
			for(int i=0; i<price.length(); i++)
			{
				if(Character.isLetter(price.charAt(i)))
				{
					validPrice.setText("Enter Digits");
					validated = false;
				}
				else
					validPrice.setText("");
			}
			
			if(disc.equals(""))
			{
				validDiscount.setText("No Discount");
				validated = false;
			}
			else
				validDiscount.setText("");	
	
			for(int i=0; i<disc.length(); i++)
			{
				if(Character.isLetter(disc.charAt(i)))
				{
					validDiscount.setText("No Characters allowed");
					validated = false;
				}
				else
					validDiscount.setText("");
			}
			
			if(total.equals(""))
			{
				validTotalQuantity.setText("No Quantity Entered");
				validated = false;
			}
			else
				validTotalQuantity.setText("");	
	
			for(int i=0; i<total.length(); i++)
			{
				if(Character.isLetter(total.charAt(i)))
				{
					validTotalQuantity.setText("Only Digits allowed");
					validated = false;
				}
				else
					validTotalQuantity.setText("");
			}
			
			if(available.equals(""))
			{
				validAvailableQuantity.setText("No Available Quantity");
				validated = false;
			}
			else
				validAvailableQuantity.setText("");	
	
			for(int i=0; i<available.length(); i++)
			{
				if(Character.isLetter(available.charAt(i)))
				{
					validAvailableQuantity.setText("No LEtters.");
					validated = false;
				}
				else
					validAvailableQuantity.setText("");
			}
			
			if(validated)
			{
				new CustomerDAO().AddProduct(Integer.parseInt(pid),pname,mfg,cat.charAt(0),Integer.parseInt(price)
						,Integer.parseInt(disc),Integer.parseInt(total),Integer.parseInt(available));
				this.validAvailableQuantity.setText("Sucessfully added.");
			}
		}
		catch(Exception e)
		{
			this.validDate.setText("Date not Selected");
		}
	}
	
	public void GoBackFunction()
	{
		try 
		{
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminPage.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
           primaryStage.setTitle("Admin Page");
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
			System.out.println("Error! " + e);
		}
	}
}