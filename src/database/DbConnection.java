package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {
	
	static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement stmt;
	private ResultSet rs;
	private static String databaseName = "musicsite";
	private static String databaseUsername = "root";
	private static String databasePassword = "s";
	private static String driver = "com.mysql.jdbc.Driver";
	
	public DbConnection()
	{
		initialize();		
	
	}
	void initialize()
	{
		
		try
		{
			Class.forName(driver);
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            connection = DriverManager.getConnection(url, databaseUsername, databasePassword);
            
        } 
		catch (Exception e) 
		{
			System.out.print(e);
        }
	}
	public ResultSet executeQueryString(String string)
	{
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(string);
			return rs;
		}
		catch(Exception e)
		{
			System.out.print("Excection in Query:");
		
		}
		return null;
	}
	public int ChangeIntoDatabase(String string)
	{
		try{
			stmt=connection.createStatement();
			
			
			return(stmt.executeUpdate(string));
			
		}
		catch(Exception e){
			System.out.println("msg(DBConnection.java):"+e+"Exception in Insertion into Database");
		}
		return 0;
		
	}
	public ResultSet getResultSet()
	{
		return rs;
	
	}

}
