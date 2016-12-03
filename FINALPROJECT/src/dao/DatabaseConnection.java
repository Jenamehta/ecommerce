
/**
 * <h1> E commerce Shop by Jena Mehta Final Year project  </h1>
 * <h2>E Commerce Program to view Product, Add to cart using MVC architecture Front end implmented using Java FX </h2>
 * </br>
 * <h2>MySQL Database  </h2>
 * Project: Final Year
 * File Name: DatabaseConnection.java
 * Package Name: DAO
 * @author Jena Mehta A20382924
 * @version 0.1
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 
 * @author Jena
 *
 */
public class DatabaseConnection
{
	/**
	 * 
	 * @return stmt
	 * @throws ClassNotFoundException e
	 * @throws SQLException e
	 */
	public static Connection createConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
		System.out.println("Connection Established.");
		return connection;
	}

	public static PreparedStatement prepareStatement(String statement) throws ClassNotFoundException, SQLException
	{
		PreparedStatement stmt = createConnection().prepareStatement(statement);
		return stmt;
	}
}
