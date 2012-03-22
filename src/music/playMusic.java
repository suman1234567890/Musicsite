package music;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;
import java.io.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class for Servlet: playMusic
 *
 */
 public class playMusic extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   private static String databaseName = "musicsite";
	private static String databaseUsername = "root";
	//private static String databaseUsername = "suman123456789";
	//private static String databasePassword = "internet";
	private static String databasePassword = "s";
	
	private static String driver = "com.mysql.jdbc.Driver";
	private Connection con=null;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public playMusic() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		play(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		play(request,response);
	}   	
	void play(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			//Class.forName(driver);
            //String url = "jdbc:mysql://localhost:3306/" + databaseName+"?autoReconnect=yes&autoReconnectForPools=true";
            //con = DriverManager.getConnection(url, databaseUsername, databasePassword);
            //con.setAutoCommit(false);
            //System.out.print(url);
			try {
    			Context initContext = new InitialContext();
    			DataSource ds = (DataSource)initContext.lookup("java:comp/env/jdbc/MyDataSource");
    			// Get a database connection
    			con = ds.getConnection();
    			} 
    		catch(java.lang.Exception e) {
    			e.printStackTrace();
    			System.err.print(e.getClass().getName());
    			System.err.println(e.getMessage());
    			}

            PreparedStatement ps = con.prepareStatement("select music,type from music where id = ?");
            String id = request.getParameter("id");
            ps.setString(1,id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
            	Blob  b = rs.getBlob("music");
            	String type=rs.getString("type");
            	System.out.print(type);
            
            response.setContentType(type);
            response.setContentLength( (int) b.length());
            InputStream is = b.getBinaryStream();
            OutputStream os = response.getOutputStream();
            byte buf[] = new byte[(int) b.length()];
            is.read(buf);
            os.write(buf);
            os.close();
            System.out.print("Successful");
            }
        }
        catch(Exception ex) {
        	ex.printStackTrace();
             System.out.println(ex.getMessage());
        }
	}
	
	
}