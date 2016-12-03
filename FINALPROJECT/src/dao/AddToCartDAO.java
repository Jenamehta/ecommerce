
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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.stage.Stage;


/**
 * This performs database opreations which are used in cart scene.
 * Operation to Database includes
 * Query to Add Product to cart
 * Delete product from cart
 * @author Jena
 *
 */
public class AddToCartDAO 
{
	public void seeProducts(Customer loginCustomer,ObservableList data, TableView<Product> ProductsTable) throws ClassNotFoundException, SQLException
	{
		String sql = "Select * from A20382924_products";
		PreparedStatement stmt = DatabaseConnection.prepareStatement(sql);
		ResultSet rst = stmt.executeQuery();
		
		for(int i=0 ; i<rst.getMetaData().getColumnCount(); i++){
            //We are using non property style for making dynamic table
            final int j = i;                
            TableColumn col = new TableColumn(rst.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                    return new SimpleStringProperty(param.getValue().get(j).toString());                        
                }                    
            });
		ProductsTable.getColumns().addAll(col);
		System.out.println("Column ["+i+"] ");
		}
		
		while(rst.next())
		{
			ObservableList row = FXCollections.observableArrayList();
			
			 for (int i = 1; i <= rst.getMetaData().getColumnCount(); i++) 
			 {
                 row.add(rst.getString(i));
                 System.out.println(row);
             }
			 
			 data.add(row);
		}
		 ProductsTable.setItems(data);		
	}
	public void DeleteBasketitems(int id)
	{
		try
		{
			String sql = "Delete from A20382924_Cart where CustomerId=?";
			PreparedStatement pst = DatabaseConnection.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("Product deleted.");
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
}
