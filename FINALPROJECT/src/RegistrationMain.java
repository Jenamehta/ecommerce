

import controller.RegistrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegistrationMain extends Application
{	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		try 
		{
			//Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RegisterCustomer.fxml"));
			//Inflate the view using the loader
            AnchorPane root = (AnchorPane) loader.load();
            //Set window title
            primaryStage.setTitle("Registration Page");
            //Create a scene with the inflated view
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
            //Set the scene to the stage
            primaryStage.setScene(scene);
            //Get the controller instance from the loader
           // AddBankController controller = loader.getController();
            RegistrationController controller = loader.getController();
            //Set the parent stage in the controller
            controller.setDialogStage(primaryStage);
            //Show the view
            primaryStage.show();

		}
		catch(Exception e)
		{
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
