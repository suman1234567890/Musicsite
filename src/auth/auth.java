package auth;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.dbConnect;

/**
 * Servlet implementation class for Servlet: auth
 *
 */
 public class auth extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public auth() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		authentication(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		authentication(request, response);
	} 
	void authentication(HttpServletRequest request, HttpServletResponse response)
	{
		String emailid=request.getParameter("emailid");
		String password=request.getParameter("password");
		dbConnect db= new dbConnect();
		String query="select emailid,question from register where emailid='"+emailid+"' and password='"+password+"'";
		System.out.print(query);
		ResultSet rs= db.executeQueryString(query);
		try{
				if(rs.next())
				{
					//String s=rs.getString(0);
					if(rs.getString(1)!=null)
					{
						String email=rs.getString(1);
						HttpSession hs= request.getSession();
						hs.setAttribute("emailid", rs.getString(1));
						hs.setAttribute("ques", rs.getString(2));
						ArrayList a=(ArrayList)getServletContext().getAttribute("CurrentUser");
						
						a.add(email);
						

						
						response.sendRedirect("pages/home/index.jsp");
					}
				}
				else
				{
					System.out.print("increct");
					response.sendRedirect("pages/home/index.jsp?message=wrong password");
					
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("Exception in auth");
		}
		
		
		
	}
}