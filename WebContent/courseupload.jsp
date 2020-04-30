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
   final File initialFile = new File(file1);
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
   String [] splitSt =str.split(":");
   String code="",name="",sem="";
   for (int i = 0 ; i < splitSt.length ; i++)
   {
    code=splitSt[0];
    name=splitSt[1];
    sem=splitSt[2];
   }

int k=st.executeUpdate("insert into lab_course(course_code,course_name,sem) values('"+code+"','"+name+"','"+sem+"')");
   
}
   RequestDispatcher rd=request.getRequestDispatcher("/newlogadmin.html");
   rd.forward(request,response);
%>

<%--<a class="button" href="Uploaded.jsp">UPLOAD Question</a>
</div>
</div>--%>

</body>

</html>



