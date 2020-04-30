<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUESTION</title>
</head>
<body style="background-color:DarkCyan;">
<%
Connection con=null;
Statement st=null;
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
//session.setAttribute("m",s);
String oper=request.getParameter("choose");
ResultSet rs=null;
String usn=(String)session.getAttribute("usn");
String course_code=(String)session.getAttribute("code");
int em=(Integer)session.getAttribute("exec_marks");
int dm=(Integer)session.getAttribute("deduct_marks");
PreparedStatement ps1=con.prepareStatement("insert into result (usn,course_code) values (?,?)");
ps1.setString(1,usn);
ps1.setString(2,course_code);
ps1.executeUpdate();
PreparedStatement ps=con.prepareStatement("update result set q_no=?,exec_marks=? where usn=? and course_code=?");
if(oper.equals("retain"))
{
	//session=request.getSession(false);
	String qno=(String)session.getAttribute("qno");
	String question=(String)session.getAttribute("question");
	//int exec_marks=(Integer)session.getAttribute("exec_marks");
	ps.setString(1,qno);
	ps.setInt(2,em);
	ps.setString(3,usn);
	ps.setString(4,course_code);
	ps.executeUpdate();
	out.println("<html><body><h1><font color='white'>"+qno+".  "+question+"</font></h1></html></body>");
	//out.println("<html><body><h3><font color='white'>ALL THE BEST!!</font></h3></html></body>");
	//out.println("<html><body><center><input type='submit' value='Logout'><center></body></html>");
}
else if(oper.equals("change"))
{
    response.setContentType("text/html");
    int count=(Integer)session.getAttribute("count");
    int min=1;
   	int max=count;
   	int range=max-min+1;
    int a=(int)(((Math.random())*range)+1);
    String x="QUESTION ALLOTTED";
    {%> <strong><p style="color:white;"><%=x %></p></strong> <br/> <%}
    //Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    String sql;
    String s=Integer.toString(a);
    String prevq=(String)session.getAttribute("qno");
    PreparedStatement p=con.prepareStatement("select q_no,question from questions where q_no=? and course_code=?");
    p.setString(1,s);
    p.setString(2,course_code);
    rs=p.executeQuery();
    rs.next();
    String z=rs.getString(2);
    z=z.replace('$',' ');
    while(prevq.equals(s))
    {
    	a=(int)(((Math.random())*range)+1);
        //Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        s=Integer.toString(a);
        p=con.prepareStatement("select q_no,question from questions where q_no=? and course_code=?");
        p.setString(1,s);
        p.setString(2,course_code);
        rs=p.executeQuery();
        rs.next();
        z=rs.getString("question");
        z=z.replace('$',' ');
    }
    em=em-dm;
	ps.setString(1,prevq);
	ps.setInt(2,em);
	ps.setString(3,usn);
	ps.setString(4,course_code);
	ps.executeUpdate();
	PreparedStatement ps2=con.prepareStatement("update result set newQ=? where usn=? and course_code=?");
	ps2.setString(1,s);
	ps2.setString(2,usn);
	ps2.setString(3,course_code);
	ps2.executeUpdate();
    //ps.setString(1,s);
    //ps.setString(2,usn);
    //ps.executeUpdate();
    out.println("<html><body><h1><font color='white'>"+s+".   "+z+"</font></h1></body></html>"); 
    //out.println("<html><body><h2><font color='white'>ALL THE BEST!!</font></h2></body></html>");
    //out.println("<html><body><center><input type='submit' value='Logout'><center></body></html>");
    
}
%>
<form action="studlogout.html" method="post">
<center><input type='submit' value='Logout'><center>
</form>
</body>
</html>