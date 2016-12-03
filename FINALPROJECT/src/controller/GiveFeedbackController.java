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
import dao.SubmitFeedbackDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GiveFeedbackController 
{
	private Stage dialogStage;
	@FXML private TextArea descriptionField;
	@FXML private TextField ratingField;
	@FXML private Button submitFeedback;
	@FXML private Label RatingLbl;
	//Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) 
	{
		this.dialogStage = dialogStage;
	}
	
	public void NavigateToLogoutPage()
	{
		boolean validate = true;
		int custId = CustomerDAO.CustomerIdentity;
		String description = this.descriptionField.getText();
		String rate = this.ratingField.getText();
		for(int i=0; i<rate.length(); i++)
		{
			if(Character.isLetter(rate.charAt(i)))
			{
				this.RatingLbl.setText("Rating should only have digits.");
				validate = false;				
			}
			else
				this.RatingLbl.setText("");
		}
		if(validate)
		{
			int rating = Integer.parseInt(this.ratingField.getText());
			new SubmitFeedbackDAO().FeedbackFormDetails(custId, description, rating);
			try 
			{
				Stage primaryStage = new Stage();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderDetails.fxml"));
			
	            AnchorPane root = (AnchorPane) loader.load();
	                 primaryStage.setTitle("Provide FeedBack");
	           
	            Scene scene = new Scene(root);
	            scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	       
	            primaryStage.setScene(scene);
	         
	            OrderDetailsController controller = loader.getController();
	            
	            controller.setDialogStage(primaryStage);
		       
		        primaryStage.show();
		        controller.GetAllOrderDetails();
		        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
			}
			catch(Exception e)
			{
				System.out.println("Error!! Something Went Wrong " + e);
			}
		}
	}
}
