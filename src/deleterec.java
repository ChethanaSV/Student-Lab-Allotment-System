import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


import javax.servlet.*;
@WebServlet("/deleterec")
public class deleterec extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			String op1=req.getParameter("op");
			String userdb;
			String pwdb;
			
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			
				if(op1.equals("deletestud")){
					RequestDispatcher rd=req.getRequestDispatcher("/deletestud.html");
					rd.forward(req,resp);
				}
				else if(op1.equals("deleteques"))
				{
					RequestDispatcher rd=req.getRequestDispatcher("/deleteques.html");
					rd.forward(req,resp);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			doGet(req,resp);
	}

}