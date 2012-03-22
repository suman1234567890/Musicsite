package list;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DbConnection;
import database.dbConnect;

/**
 * Servlet implementation class for Servlet: menulist
 *
 */
 public class menulist extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public menulist() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		show(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		show(request,response);
	}   	  
	void show(HttpServletRequest request,HttpServletResponse response)
	{
		PrintWriter pw=null;
		String s=request.getParameter("keyword");
		String ss=s.toLowerCase().replace(" ", "");
		
		//DbConnection db = new DbConnection();
		dbConnect db= new dbConnect();
		String sss="select * from musicdetail where language='"+ss+"' or type='"+ss+"' or singer='"+ss+"' or year='"+ss+"' or title='"+ss+"'";
		ResultSet rs=db.executeQueryString("select * from musicdetail where language='"+ss+"' or type='"+ss+"' or singer='"+ss+"' or year='"+ss+"' or title='"+ss+"'");
		System.out.print(sss);
		
		try{
			pw= response.getWriter();
			pw.print("<channel>");
			while(rs.next())
			{
				pw.print("<item>");
				pw.print("<Album>");
				pw.print(rs.getString(4));
				
				pw.print("</Album>");
				pw.print("<Singer>"+rs.getString(5));
				
				pw.print("</Singer>");
				pw.print("<Year>"+rs.getString(6));
						
				
				pw.print("</Year>");
				pw.print("<songid>");
				if(rs.getString(7).compareTo("video/x-flv")==0)
				{
					pw.print(rs.getString(1)+"1");
				
				}
				else
					
					{
						pw.print(rs.getString(1)+"0");
					
					}
				
				pw.print("</songid>");
				pw.print("</item>");
							
			}
			pw.print("</channel>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("Exception in menu list"+e);
			
		}
		
				
	}
}