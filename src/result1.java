import java.io.*;
import java.sql.*;
import java.util.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
@WebServlet("/result1")

public class result1 extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			String code=req.getParameter("code");
			String code1=code.toUpperCase();
			String section1=req.getParameter("section");
			String sem1=req.getParameter("sem");
			String codedb;
			String sectiondb;
			String semdb,usndb;
			int q_nodb,exec_marksdb;
			PrintWriter out=resp.getWriter();
			resp.setContentType("text/html");
			//List<String>data = new ArrayList<String>();
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
				String filename="";
				if(section1.equals("A"))
					filename="result1.csv";
				else if(section1.equals("B"))
					filename="result2.csv";
				else if(section1.equals("C"))
					filename="result3.csv";
				else if(section1.equals("All"))
					filename="result4.csv";
				FileWriter fw = new FileWriter(filename);
				fw.append("Section");
				fw.append(",");
				fw.append("USN");
				fw.append(",");
				fw.append("Question number");
				fw.append(",");
				fw.append("Changed Question");
				fw.append(",");
				fw.append("Execution marks");
				fw.append("\n");
				PreparedStatement ps=null;
				if(section1.equals("All"))
				{
					ps=con.prepareStatement("select section,r.usn,q_no,newQ,exec_marks from result r join student s on r.usn=s.usn where course_code=?");
					ps.setString(1,code1);
				}
				else
				{
					ps=con.prepareStatement("select section,r.usn,q_no,newQ,exec_marks from result r join student s on r.usn=s.usn where section=? and course_code=?");
					ps.setString(1,section1);
					ps.setString(2,code1);
				}
				ResultSet rs1=ps.executeQuery();
			//ResultSet rs=st.executeQuery("select * from result");
			//OutputStream outputStream = resp.getOutputStream();
			//String outputResult = "<html><body><table><tr><th>usn</th><th>question number</th><th>changed question</th><th>execution marks</th></tr>";
			//String outputResult = "this is a test";
			//outputStream.write(outputResult.getBytes());
			//out.println("<html><body><table><tr><th>usn</th><th>question number</th><th>changed question</th><th>execution marks</th></tr>");
				while(rs1.next())
				{
				 	//String usn=rs1.getString(1);
					//String qno=rs1.getString(2);
					//String newq=rs1.getString(3);
					//int exec_marks=rs1.getInt(4);
					//data.add(usn + "\t\t" + qno + "\t\t" + newq + "\t\t" + exec_marks);
					fw.append(rs1.getString(1));
					fw.append(",");
					fw.append(rs1.getString(2));
					fw.append(",");
					fw.append(rs1.getString(3));
					fw.append(",");
					fw.append(rs1.getString(4));
					fw.append(",");
					fw.append(rs1.getString(5));
					fw.append('\n');
					
				}
				fw.flush();
				fw.close();
				
				//writeToFile(data, "employee.txt");
				RequestDispatcher rd=req.getRequestDispatcher("/reslogout.html");
				rd.include(req,resp);
		}
		else{
					out.println("<html><body><h3><font color=\"white\">Invalid course code or section.<br>Please enter again</font></h3></body></html>");
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
	/*private static void writeToFile(java.util.List<String> list, String path) {
		BufferedWriter out = null;
		try {
			//path = "C:\\Users\\Deel\\Desktop\\DBMS Project\\employee.txt";
			File file = new File(path);
			out = new BufferedWriter(new FileWriter(file, true));
			for (Object s : list) {
				out.write((String) s);
				out.newLine();

			}
			out.close();
		} catch (IOException e) {
		}
	}*/

}