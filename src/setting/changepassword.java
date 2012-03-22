package setting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.dbConnect;

/**
 * Servlet implementation class for Servlet: changepassword
 *
 */
 public class changepassword extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public changepassword() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		change(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		change(request,response);
	}  
	void change(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession hs= request.getSession();
		
		String emailid=hs.getAttribute("emailid").toString();
		String newpass=request.getParameter("newpass");
		String prevpass=request.getParameter("prevpass");
		dbConnect db=new dbConnect();
		String query="update register set password='"+newpass+"' where emailid='"+emailid+"' and password='"+prevpass+"'";
		int i=db.insertQueryString(query);
		
			try{
				if (i==0)
				{
					PrintWriter pw= response.getWriter();
					pw.write("'message':"+"'unSuccessful'");
				}
				if (i!=0)
				{
					PrintWriter pw= response.getWriter();
					pw.write("'message':"+"'Successful'");
				}
			}
			catch(Exception e)
			{
				System.out.print("error");
			}
			
		
	}
}