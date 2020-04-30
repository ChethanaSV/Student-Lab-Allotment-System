import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
@WebServlet("/course")
public class course extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			String code=req.getParameter("code");
			String code1=code.toUpperCase();
			ResultSet rs=st.executeQuery("select * from lab_course");
			//String name1=req.getParameter("name");
			int exec1=Integer.parseInt(req.getParameter("exec"));
			int deduct1=Integer.parseInt(req.getParameter("deduct"));
			//String sem1=req.getParameter("sem");
			//String op1=req.getParameter("op");
			String codedb;
			//String namedb;
			//String semdb;
			int execdb,deductdb;
			/*PreparedStatement ps=con.prepareStatement("update lab_course set exec_marks=? and deduct_marks=? where course_code=?");
			ps.setInt(1,exec1);
			ps.setInt(2,deduct1);
			ps.setString(3,code1);*/
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			while(rs.next())
			{
				codedb=rs.getString(1);
				//namedb=rs.getString(2);
				//semdb=rs.getString(3);
				if(codedb.equals(code1))
				{
					PreparedStatement ps=con.prepareStatement("update lab_course set exec_marks=?,deduct_marks=? where course_code=?");
					ps.setString(3,code1);
					ps.setInt(1,exec1);
					ps.setInt(2,deduct1);
					
					ps.executeUpdate();
					flag=1;
					break;
				}
			}
			if(flag==1) {
				HttpSession session=req.getSession(false);
				session.setAttribute("course_code",code1);
				session.setAttribute("exec_marks",exec1);
				session.setAttribute("deduct_marks",deduct1);
				RequestDispatcher rd=req.getRequestDispatcher("/upload.html");
				rd.forward(req,resp);
				}
				/*else {
					RequestDispatcher rd=req.getRequestDispatcher("/result.html");
					rd.forward(req,resp);
				}*/
			
			
			else {
				out.println("<h3><font color=\"white\">Invalid course code <br>Please enter correct details</font></h3>");
				RequestDispatcher rd=req.getRequestDispatcher("/subjectdetails.html");
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