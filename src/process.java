import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
@WebServlet("/process")
public class process extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			String op=req.getParameter("but");
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			if(op.equals("viewfile"))
			{
				RequestDispatcher rd=req.getRequestDispatcher("/view.jsp");
				rd.forward(req,resp);
			}
			else if(op.equals("logout"))
			{
				RequestDispatcher rd=req.getRequestDispatcher("/thank.html");
				rd.forward(req,resp);
			}
			else if(op.equals("back"))
			{
				RequestDispatcher rd=req.getRequestDispatcher("/teacher.html");
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