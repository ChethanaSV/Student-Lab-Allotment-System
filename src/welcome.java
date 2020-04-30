import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/welcome")
public class welcome extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			//ResultSet rs=st.executeQuery("select * from student");
			String select1=req.getParameter("select");
			/*String pw1=req.getParameter("pw");
			String section1=req.getParameter("section");
			int sys1=Integer.parseInt(req.getParameter("sys"));		
			String usndb;
			String pwdb;
			String sectiondb;
			int sysdb;*/
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			/*while(rs.next())
			{
				usndb=rs.getString(1);
				pwdb=rs.getString(2);
				sectiondb=rs.getString(3);
				//sysdb=rs.getInt(4);
				if(usndb.equals(usn1) && pwdb.equals(pw1) && sectiondb.equals(section1))
				{
					flag=1;
					break;
				}
			}*/
			if(select1.equals("student"))
			{
				RequestDispatcher rd=req.getRequestDispatcher("/student.html");
				rd.forward(req,resp);
			}
			else if(select1.equals("teacher")) {
				RequestDispatcher rd=req.getRequestDispatcher("/teacher.html");
				rd.forward(req,resp);
			}
			else if(select1.equals("admin")) {
				RequestDispatcher rd=req.getRequestDispatcher("/admin.html");
				rd.forward(req,resp);
			}
			
			/*if(flag==1) {
				//out.println("<h3>Correct credentials</h3>");
				RequestDispatcher rd=req.getRequestDispatcher("/MQuestionhtml.html");
				rd.forward(req,resp);
			}
			else {
				out.println("<h3><font color=\"white\">Wrong credentials<br>Please enter correct credentials</font></h3>");
				RequestDispatcher rd=req.getRequestDispatcher("/student.html");
				rd.include(req,resp);
			}*/
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			doGet(req,resp);
	}

}