<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View file</title>
</head>
<body style="background-color:DarkSeaGreen;">
<%
	response.setContentType("text/html");
	Connection con=null;
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
	String code=(String)session.getAttribute("course_code");
	ResultSet rs=null;
	PreparedStatement ps=con.prepareStatement("select * from questions where course_code=?");
	ps.setString(1,code);
	rs=ps.executeQuery();
	out.println("<font color='white' size='4'><table><tr><th>Course code</th><th>Q.no</th><th>Question</th></tr>");
	while(rs.next())
	{
		String code1=rs.getString(1);
		String qno=rs.getString(2);
		String question=rs.getString(3);
		out.println("<tr><td>"+code1+"</td><td>"+qno+"</td><td>"+question+"</td></tr>");
	}
	out.println("</table></font>");
%>
<form action="backteach" method="post">
<button name="but" type="submit" value="back">Back</button>
<button name="but" type="submit" value="logout">Logout</button>
</form>
</body>
</html>