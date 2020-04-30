import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
@WebServlet("/result")
public class result extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			String code1=req.getParameter("code");
			String section1=req.getParameter("section");
			String sem1=req.getParameter("sem");
			String codedb;
			String sectiondb;
			String semdb,usndb;
			int q_nodb,exec_marksdb;
			int em,dm;
			//HttpSession session=req.getSession(false);
			//int exec_marks=(Integer)session.getAttribute("exec_marks");
		    //int deduct_marks=(Integer)session.getAttribute("deduct_marks");
		    //int exec_marks1=exec_marks-deduct_marks;
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			ResultSet rs=st.executeQuery("select * from result");
			while(rs.next())
			{
				if(rs.getString(2).equals(code1)) {
					flag=1;
					break;
				}
			}
			if(flag==1) {
			PreparedStatement ps=con.prepareStatement("select r.usn,q_no,newQ,exec_marks from result r join student s on r.usn=s.usn where section=? and course_code=?");
			ps.setString(1,section1);
			ps.setString(2,code1);
			ResultSet rs1=ps.executeQuery();
			//ResultSet rs=st.executeQuery("select * from result");
			out.println("<html><body><table><tr><th>usn</th><th>question number</th><th>changed question</th><th>execution marks</th></tr>");
			while(rs1.next())
			{
				 	String usn=rs1.getString(1);
					String qno=rs1.getString(2);
					String newq=rs1.getString(3);
					int exec_marks=rs1.getInt(4);
					out.println("<tr><td>"+usn+"</td><td>"+qno+"</td><td>"+newq+"</td><td>"+exec_marks+"</td></tr>");
			}
			out.println("</table></body></html>");
			RequestDispatcher rd=req.getRequestDispatcher("/reslogout.html");
			rd.include(req,resp);
		}
			else{
					out.println("<html><body><h3>Invalid course code or section.<br>Please enter again</h3></body></html>");
					RequestDispatcher rd=req.getRequestDispatcher("/result.html");
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