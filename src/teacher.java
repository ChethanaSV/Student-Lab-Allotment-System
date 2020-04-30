import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


import javax.servlet.*;
@WebServlet("/teacher")
public class teacher extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from teacher");
			String user1=req.getParameter("user");
			String pw1=req.getParameter("pw");
			String op1=req.getParameter("op");
			String userdb;
			String pwdb;
			
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			
			while(rs.next())
			{
				userdb=rs.getString(1);
				pwdb=rs.getString(2);
				if(userdb.equals(user1) && pwdb.equals(pw1))
				{
					flag=1;
					break;
				}
			}
			if(flag==1) {
				HttpSession session=req.getSession();
				session.setAttribute("username", user1);
				if(op1.equals("upload")){
				RequestDispatcher rd=req.getRequestDispatcher("/subjectdetails.html");
				rd.forward(req,resp);
				}
				else if(op1.equals("result")){
					RequestDispatcher rd=req.getRequestDispatcher("/Result.html");
					rd.forward(req,resp);
				}
				else if(op1.equals("deleteques"))
				{
					RequestDispatcher rd=req.getRequestDispatcher("/deleteques.html");
					rd.forward(req,resp);
				}
					
				
			}
			/*if(flag==1) {
				RequestDispatcher rd=req.getRequestDispatcher("/subjectdetails.html");
				rd.forward(req,resp);
			}*/
			else {
				out.println("<h3><font color=\"white\">Wrong credentials<br>Please enter correct credentials</font></h3>");
				RequestDispatcher rd=req.getRequestDispatcher("/teacher.html");
				rd.include(req,resp);
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