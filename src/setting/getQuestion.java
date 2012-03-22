package setting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dbConnect;

/**
 * Servlet implementation class for Servlet: getQuestion
 *
 */
 public class getQuestion extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public getQuestion() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getQues(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getQues(request,response);
	}   	
	void getQues(HttpServletRequest request, HttpServletResponse response)
	{
		String emailid=request.getParameter("emailid");
		
		dbConnect db= new dbConnect();
		String query="select question from register where emailid='"+emailid+"'";
		System.out.print(query);
		ResultSet rs= db.executeQueryString(query);
		try
		{	
			PrintWriter pw1=response.getWriter();
			if(rs.next())
			{
				pw1.print("question : "+rs.getString(1));
			}
			else
			{
				pw1.print("question : No In Database");
			}
					
			
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
}