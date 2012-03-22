package setting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dbConnect;

/**
 * Servlet implementation class for Servlet: register
 *
 */
 public class register extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public register() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		reg(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		reg(request,response);
	}   
	void reg(HttpServletRequest request, HttpServletResponse response)
	{
		String emailid=request.getParameter("emaild");
		String password=request.getParameter("password");
		String question= request.getParameter("question");
		String answer=request.getParameter("answer");
		dbConnect db= new dbConnect();
		db.insertQueryString("insert into register values('"+emailid+"','"+password+"','"+question+"','"+answer+"')");
	}
}