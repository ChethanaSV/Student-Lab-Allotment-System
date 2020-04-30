/*import java.io.*;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/upload")
public class upload extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try 
        {
        	Connection con=null;
        	Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from student");
			String file1=req.getParameter("file1");
			/*String pw1=req.getParameter("pw");
			String section1=req.getParameter("section");
			int sys1=Integer.parseInt(req.getParameter("sys"));		
			String usndb;
			String pwdb;
			String sectiondb;
			int sysdb;*/
			/*resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			int flag=0;
			String text="";
			text = new String(Files.readAllBytes(Paths.get("file1")));
			BufferedReader br = new BufferedReader(new FileReader("file1"));
		    StringBuilder sb = new StringBuilder();

		    String line = br.readLine();
		    while (line != null) {
		      sb.append(line).append("\n");
		      line = br.readLine();
		    }

		    String fileAsString = sb.toString();
		    //System.out.println("whole file as String using BufferedReader and StringBuilder");
		    //System.out.println(fileAsString);
			PreparedStatement ps=con.prepareStatement("insert into questionbank values(?)");
			ps.setString(1,fileAsString);
			Boolean ch=ps.execute();
			if(ch==true) {
				out.println("Your file has been uploaded");
				RequestDispatcher rd=req.getRequestDispatcher("/logout.html");
				rd.forward(req, resp);
			}
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
			}
			if(flag==1) {
				//out.println("<h3>Correct credentials</h3>");
				RequestDispatcher rd=req.getRequestDispatcher("/MQuestionhtml.html");
				rd.forward(req,resp);
			}
			else {
				out.println("<h3><font color=\"white\">Wrong credentials<br>Please enter correct credentials</font></h3>");
				RequestDispatcher rd=req.getRequestDispatcher("/student.html");
				rd.include(req,resp);
			}*/
		/*}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			doGet(req,resp);
	}

}*/
/*import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.annotation.WebServlet;

@WebServlet("/upload") 
/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
/*public class upload extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "C:/uploads";
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
               
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
            
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
          
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
     
        request.getRequestDispatcher("/result.jsp").forward(request, response);
      
    }
   
}*/
/*import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
@WebServlet("/upload")
public class upload extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment", "root", "");
		Statement st=con.createStatement();
		//String stng=request.getParameter("path");
		   //InputStreamReader reader = new InputStreamReader(inputStream);
		   final File initialFile = new File("C:\\Users\\Deel\\Desktop\\DBMS Project\\qs.txt");
		      final InputStream targetStream =
		        new DataInputStream(new FileInputStream(initialFile));
		   BufferedReader br = new BufferedReader(new InputStreamReader(targetStream));
		   String strLine;
		   ArrayList list=new ArrayList();
		   while ((strLine = br.readLine()) != null)
		   {
		   list.add(strLine);
		   }
		 Iterator itr;
		   for (itr=list.iterator(); itr.hasNext(); )
		   {
		   String str=itr.next().toString();  
		   String [] splitSt =str.split(" ");
		   String sc="",qn="",q="";
		   for (int i = 0 ; i < splitSt.length ; i++)
		   {
		    sc=splitSt[0];
		    qn=splitSt[1];
		    q=splitSt[2];
		   }

		int k=st.executeUpdate("insert into foo(Subcode,QNo,Q) values('"+sc+"','"+qn+"','"+q+"')");
		   
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
*/


