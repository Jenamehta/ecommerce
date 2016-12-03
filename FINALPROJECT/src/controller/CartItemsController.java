/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name: CartItemsControllers.java
 * Package Name: controllers
 * @author Jena Mehta A20382924
 * @version 0.1
 */


package controller;

import java.io.IOException;
import java.util.List;

import dao.CartItemsDao;
import dao.ProductDAO;
import model.Customer;
import model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * CartItemsController
 * @author Jena
 *
 */
public class CartItemsController 
{
	//This is the parent stage
	private Stage dialogStage;
	@FXML private TableView cartProducts = new TableView<Product>();
	@FXML private Button placeOrder;
	@FXML private ComboBox<String> CategoryBox = new ComboBox<String>();
	@FXML private Button detailsButton;
	@FXML private Label errorLabel;
	private ObservableList data = FXCollections.observableArrayList();
	//Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) 
	{
		this.dialogStage = dialogStage;
	}
/**
 * GetCartProducts: to get products on cart
 * This will help in view productlist	
 */
	public void GetCartProducts()
	{
		List<Product> allItemsinCart = Customer.productList;
		System.out.println("In CartItemsController cart size= " + allItemsinCart.size());
		System.out.println("ho ithe " + allItemsinCart.size());
		new CartItemsDao().SeeAllElementsInBasket(data, cartProducts);
		CategoryBox.getItems().addAll("Kitchen","Electronics","Books","Furniture","");
	}	
	/**
	 * Get order details of customer
	 * 
	 * @throws IOException e
	 * Resource : ORderDetails.fxml
	 * To control order details this class will fetch results from customer order table and 
	 * 
	 */
	public void GetOrderDetails() throws IOException
	{
		try 
		{
			Stage primaryStage = new Stage();
			
			
			//This will load UI i.e OrderDetails.fxml
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderDetails.fxml"));
			
			
			//To populate the values
			
			
            AnchorPane root = (AnchorPane) loader.load();
            primaryStage.setTitle("View Cart Page");
           
            Scene scene = new Scene(root);
            
            //Use application.css
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
			System.out.println("Error!! Something went Wrong " + e);
		}
	}
	/**
	 * Provide Category details
	 */
	public void ShowDetails()
	{
		boolean validate = true;
		String category = this.CategoryBox.getValue();
		if(category == "" || category.equals(""))
		{
			this.errorLabel.setText("Need to select Category.");
			validate = false;
		}
		
		if(validate)
		{
			new ProductDAO().ShowProductDetails(category,errorLabel);
		}
	}
}