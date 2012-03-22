package music;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.PrintWriter;
import java.sql.*;
import java.util.List;



import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import database.dbConnect;

/**
 * Servlet implementation class for Servlet: AddPhotoServlet
 *
 */
 public class AddPhotoServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
	private static String databaseName = "musicsite";
	private static String databaseUsername = "root";
	private static String databasePassword = "s";
	private static String driver = "com.mysql.jdbc.Driver";
	private Connection con=null;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AddPhotoServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Apache Commons-Fileupload library classes
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu  = new ServletFileUpload(factory);
            

            if (! ServletFileUpload.isMultipartContent(request)) {
                System.out.println("sorry. No file uploaded");
                return;
            }

            // parse request
            List items = sfu.parseRequest(request);
            FileItem  language = (FileItem) items.get(0);
            String musiclang =  language.getString();
            musiclang=musiclang.toLowerCase().replace(" ", "");
            
            FileItem type1 = (FileItem) items.get(1);
            String   musictype =  type1.getString();
            musictype=musictype.toLowerCase().replace(" ", "");
            
            FileItem title = (FileItem) items.get(2);
            String   musictitle =  title.getString();
            musictitle=musictitle.toLowerCase().replace(" ", "");
            FileItem singer = (FileItem) items.get(3);
            String   musicsinger =  singer.getString();
            musicsinger=musicsinger.toLowerCase().replace(" ", "");
            
            FileItem year = (FileItem) items.get(4);
            String   musicyear =  year.getString();
            musicyear=musicyear.toLowerCase().replace(" ", "");

            // get uploaded file
            FileItem file = (FileItem) items.get(5);
            System.out.print(file.getContentType());
            
                       
            // Connect to Oracle
            //Class.forName(driver);
            //String url = "jdbc:mysql://localhost:3306/" + databaseName+"?autoReconnect=yes&autoReconnectForPools=true";
            //System.out.print(url + databaseName+databasePassword);
            
            //con = DriverManager.getConnection(url, databaseUsername, databasePassword);
            //con.setAutoCommit(false);
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
    		dbConnect db= new dbConnect();
    		db.insertQueryString("insert into musicdetail(language,type,title,singer,year,cateogory) values('"+musiclang+"','"+musictype+"','"+musictitle+"','"+musicsinger+"','"+musicyear+"','"+file.getContentType()+"')");
    		ResultSet rs=db.executeQueryString("select id from musicdetail where language='"+musiclang+"' and type='"+musictype+"'and title='"+musictitle+"' and singer='"+musicsinger+"' and year='"+musicyear+"'");
    		rs.next();
    		String id= rs.getString("id");
            PreparedStatement ps = con.prepareStatement("insert into music values(?,?,?,?)");
            ps.setString(1, id);
            ps.setString(2, musictitle);
            // size must be converted to int otherwise it results in error
            ps.setBinaryStream(3, file.getInputStream(), (int) file.getSize());
            ps.setString(4, file.getContentType());
            ps.executeUpdate();
            con.commit();
            con.close();
            //out.println("Proto Added Successfully. <p> <a href='listphotos'>List Photos </a>");'
            response.sendRedirect("pages/home/index.jsp");
        }
        catch(Exception ex) {
            out.println( "Error --> " + ex.getMessage());
            ex.printStackTrace();
        }
	}   	
	
}