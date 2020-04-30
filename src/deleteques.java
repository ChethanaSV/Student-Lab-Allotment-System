import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


import javax.servlet.*;
@WebServlet("/deleteques")
public class deleteques extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			String code1=req.getParameter("code");
			String code=code1.toUpperCase();
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			ResultSet rs=st.executeQuery("select * from questions");
			while(rs.next())
			{
				if(rs.getString(1).equals(code))
				{
					flag=1;
					break;
				}
			}
			if(flag==1) {
				PreparedStatement ps=con.prepareStatement("delete from questions where course_code=?");
				ps.setString(1,code);
				ps.executeUpdate();
				RequestDispatcher rd=req.getRequestDispatcher("/dellogout.html");
				rd.forward(req,resp);
				
			}
			else
			{
				out.println("<html><body><h3><font color='white'>Invalid course code<br>Please enter correct course code<br></font></h3></body></html>");
				RequestDispatcher rd=req.getRequestDispatcher("/deleteques.html");
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