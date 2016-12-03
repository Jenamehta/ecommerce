
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

import dao.AddToCartDAO;
import dao.CustomerDAO;
import dao.OrderDetailsDAO;
import model.Basket;
import model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class OrderDetailsController
{
	private Stage dialogStage;
	@FXML private Label greetUser;
	@FXML private Label userAddress;
	@FXML private Label totalBill;
	@FXML private Button logOut;
	@FXML private Button feedbackBtn;
	@FXML private Label placedOrder;
	@FXML private PasswordField updatePassword;
	@FXML private Button updateAccount;
	@FXML private Label passwordUpdatedLbl;
	
	public void setDialogStage(Stage dialogStage) 
	{
		this.dialogStage = dialogStage;
	}
	
	public void GetAllOrderDetails()
	{
		Customer c = new CustomerDAO().GetCustomerById(CustomerDAO.CustomerIdentity);
		Basket b = new OrderDetailsDAO().GetBillAmount(c.getCustomer_Id());
		if(b.getTotalBillAmount() == 0)
		{
			this.greetUser.setText("Welcome, "+ c.getFirst_Name());
			this.totalBill.setText("No products!! ");
			this.userAddress.setText(c.getAddress());		
		}
		else
		{
			this.greetUser.setText("Hello, "+ c.getFirst_Name());
			this.userAddress.setText(c.getAddress());
			this.placedOrder.setText("Order is placed");
			this.totalBill.setText("Total value of Cart is" + b.getTotalBillAmount() + " This is payable as Cash on Delivery " + b.getDeliveryDate() + ".");
			new OrderDetailsDAO().AddOrderDetailsInfo(b);
		}
 	}
	
	public void LogOutCustomer()
	{
		new AddToCartDAO().DeleteBasketitems(CustomerDAO.CustomerIdentity);
		CustomerDAO.CustomerIdentity = 0;
        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
        
        try
		{
        	Stage primaryStage = new Stage();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fp/view/Login.fxml"));
			            AnchorPane root = (AnchorPane) loader.load();
                        primaryStage.setTitle("Login Page");
                        Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/fp/view/application.css").toExternalForm());
                        primaryStage.setScene(scene);
                        LoginController controller = loader.getController();
                        controller.setDialogStage(primaryStage);
                        primaryStage.show();
	        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}
		catch(Exception e)
		{
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	public void GiveFeedback()
	{
		try 
		{
			Stage primaryStage = new Stage();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GiveFeedback.fxml"));
			            AnchorPane root = (AnchorPane) loader.load();
                        primaryStage.setTitle("Give A Feedback Page");
                        Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
                        primaryStage.setScene(scene);
                        GiveFeedbackController controller = loader.getController();
                        controller.setDialogStage(primaryStage);
	        	        primaryStage.show();
	        	        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}
		catch(Exception e)
		{
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	public void UpdateAccount()
	{
		String newPassword = this.updatePassword.getText();
		new Customer().EditCustomerDetails(newPassword, CustomerDAO.CustomerIdentity);
		this.passwordUpdatedLbl.setText("Password Updated Successfully.");
	}
}