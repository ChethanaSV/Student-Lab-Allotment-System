import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


import javax.servlet.*;
@WebServlet("/deletestud")
public class deletestud extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			String usn=req.getParameter("usn");
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			ResultSet rs=st.executeQuery("select * from student");
			while(rs.next())
			{
				if(rs.getString(1).contains(usn))
				{
					flag=1;
					break;
				}
			}
			if(flag==1) {
				PreparedStatement ps1=con.prepareStatement("delete from result where usn like concat('%',?,'%')");
				ps1.setString(1,usn);
				ps1.executeUpdate();
				PreparedStatement ps=con.prepareStatement("delete from student where usn like concat('%',?,'%')");
				ps.setString(1,usn);
				ps.executeUpdate();
				RequestDispatcher rd=req.getRequestDispatcher("/deladmlog.html");
				rd.forward(req,resp);
				
			}
			else
			{
				out.println("<html><body><h3><font color='white'>Invalid USN prefix<br>Enter correct prefix<br></font></h3></body></html>");
				RequestDispatcher rd=req.getRequestDispatcher("/deletestud.html");
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