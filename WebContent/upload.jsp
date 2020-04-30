<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<body>
<br>
<br>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment", "root", "");
Statement st=con.createStatement();
//String stng=request.getParameter("path");
   //InputStreamReader reader = new InputStreamReader(inputStream);
   String file1=request.getParameter("file");
   //String query = "LOAD DATA INFILE '"+file1+"' INTO TABLE questions FIELDS TERMINATED BY ',' (course_code,q_no,question)";
   //st.executeUpdate(query);
   File initialFile = new File(file1);
   //System.out.println(initialFile.getPath());
   //String getpath1=initialFile.getAbsolutePath();
   //System.out.println(getpath1);
   InputStream targetStream=new DataInputStream(new FileInputStream(initialFile));
   BufferedReader br = new BufferedReader(new InputStreamReader(targetStream));
   String strLine;
   ArrayList<String> list=new ArrayList<String>();
   String str1="";
   while ((strLine = br.readLine()) != null)
   {
	   str1=str1+strLine+'\n';
	   System.out.println(str1);
	   char[] c=strLine.toCharArray();
	   int index=strLine.indexOf('$');
	   if(index!=-1){
		   list.add(str1);
		   str1="";
	   }
   }
System.out.println(list);
 Iterator itr;
   for (itr=list.iterator(); itr.hasNext(); )
   {
   String str=itr.next().toString();  
   String [] splitSt =str.split("~");
   String sc="",qn="",q="";
   for (int i = 0 ; i < splitSt.length ; i++)
   {
    sc=splitSt[0];
    qn=splitSt[1];
    q=splitSt[2];
   }

int k=st.executeUpdate("insert into questions(course_code,q_no,question) values('"+sc+"','"+qn+"','"+q+"')");
   
}
   RequestDispatcher rd=request.getRequestDispatcher("/logout.html");
   rd.forward(request,response);
%>
<%--<a class="button" href="Uploaded.jsp">UPLOAD Question</a>
</div>
</div>--%>
</form>
</body>
</html>



