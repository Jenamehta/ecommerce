/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name: AddToCartController.java
 * Package Name: controllers
 * @author Jena Mehta A20382924
 * @version 0.1
 */
package controller;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.AddToCartDAO;
import dao.CartItemsDao;
import model.Customer;
import model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * 
 * @author Jena Mehta
 *
 */
public class AddToCartController
{
	//This is the parent stage
	private Stage dialogStage;
	
	//This is the Text box element in the view for name of bank
	@FXML private Button allProducts;
	@FXML private TableView<Product> ProductsTable = new TableView<Product>();
	@FXML public TextField productId;
	@FXML private Button addToCart;
	@FXML private TextField deleteFromCart;
	@FXML private Button deleteBtn;
	@FXML private Label productLabel;
	@FXML private Label errorMsgLabel;
	private ObservableList data = FXCollections.observableArrayList();
	//Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) 
	{
		this.dialogStage = dialogStage;
	}
	/**
	 * 
	 * @param loginCustomer To_get_cust_id
	 */
	public void GetAllProductsList(Customer loginCustomer)
	{
		try 
		{
			System.out.println("In AddtoCartController = GetAllProductsList()");
			new AddToCartDAO().seeProducts(loginCustomer,data, ProductsTable);
		} 
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void AddProductToCart()
	{
		//Validates that only numbers are passed in textfied
		
		boolean validate = true;
		String id = this.productId.getText();
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(id);
		if(!matcher.matches())
		{
			errorMsgLabel.setText("Wrong ID Format");
			validate = false;
		}
		else
			errorMsgLabel.setText("");
		
		if(validate)
		{
			int product_id = Integer.parseInt(id);
			System.out.println("Connected");
			Product newadded = new CartItemsDao().Add_Item_To_List(product_id);
			if(newadded.getProduct_Name().equals(""))
			{
				productLabel.setText("Product not Exist");
				
			}
			else
			{
				productLabel.setText("");
				new CartItemsDao().Add_Item_To_Cart(newadded);
				productLabel.setText("Product Added");
				Customer.productList.add(newadded);
			}
		}
	}
	/**
	 * To view products onto cart
	 */
	public void SeeProductsCart()
	{
		try 
		{
			Stage primaryStage = new Stage();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CartItems.fxml"));
			
            AnchorPane root = (AnchorPane) loader.load();
     
            primaryStage.setTitle("Cart Items Page");

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/application1.css").toExternalForm());
     
            primaryStage.setScene(scene);
           
            CartItemsController controller = loader.getController();
   
            controller.setDialogStage(primaryStage);
	     
	        primaryStage.show();
	        controller.GetCartProducts();
	        
	        dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}
		catch(Exception e)
		{
			System.out.println("Error!! Something Went Wrong " + e);
		}
	}
	/**
	 * To delete product from cart
	 */
	public void DeleteProductFromCart()
	{
		boolean validate = true;
		String pid = this.deleteFromCart.getText();
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(pid);
		if(!matcher.matches())
		{
			errorMsgLabel.setText("Only Digits Allowed");
			validate = false;
		}
		else
			errorMsgLabel.setText("");
		if(validate)
		{
			int id = Integer.parseInt(pid);
			new CartItemsDao().DeleteCartProduct(errorMsgLabel,id);
		}
	}
}