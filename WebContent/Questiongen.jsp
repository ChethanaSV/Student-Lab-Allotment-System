<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body { text-align:center;}
</style>
<meta charset="UTF-8">
<title>Question generated</title>
</head>
<body style="background-color:IndianRed;">
<%
 
    // Get print writer.
    PrintWriter sout = response.getWriter();
    response.setContentType("text/html");
    //int a=(int)(((Math.random())*3)+1);
    String x="QUESTION ALLOTTED";
    {%> <strong><p style="color:white;"><%=x %></p></strong> <br/> <%}
    Connection con=null;
    Class.forName("com.mysql.jdbc.Driver");
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_allotment","root","");
    //Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    String sql;
    String code1=(String)session.getAttribute("code");
    PreparedStatement ps1=con.prepareStatement("select count(*) from questions where course_code=?");
    ps1.setString(1,code1);
    ResultSet rs1=ps1.executeQuery();
    rs1.next();
    int count=rs1.getInt(1);
   	session.setAttribute("count",count);
   	int min=1;
   	int max=count;
   	int range=max-min+1;
    int a=(int)(((Math.random())*range)+1);
    String s=Integer.toString(a);
    session.setAttribute("qno",s);
    PreparedStatement p=con.prepareStatement("select course_code,q_no,question from questions where q_no=? and course_code=?");
    p.setString(1,s);
    p.setString(2,code1);
    ResultSet rs=p.executeQuery();
    rs.next();
    String course_code=rs.getString(1);
    String qno=rs.getString(2);
    String z=rs.getString(3);
    z=z.replace('$',' ');
    session.setAttribute("question",z);
    //session.setAttribute("course_code",course_code);
    out.println("<html><body><h1><font color='white'>"+s+".   "+z+"</font></h1></body></html>");
 

%>
<form action="Next.jsp" method="post">    
<button name="choose" type="submit" value="retain">RETAIN</button>
<button name="choose" type="submit" id="btnCounter" value="change">CHANGE</button>
<br>
<br>
<h3><font color="white" size="6">Time left:   <span id="mins"></span>:<span id="secs"></span></font></h3>
</form>
</body>
<script>
var spn1 = document.getElementById("mins");
var spn2 = document.getElementById("secs");
var btn = document.getElementById("btnCounter");
//var count = 5;     // Set count
var mins = 1; // write mins to javascript
var secs = 0; // write secs to javascript
var timer = null;  // For referencing the timer

(function countDown(){
  // Display counter and start counting down
  //spn.textContent = count;
  // Run the function again every second if the count is not zero
  /*if(count !== 0){
    timer = setTimeout(countDown, 1000);
    count--; // decrease the timer
  } */
  spn1.textContent = mins;
  spn2.textContent=secs;
  if( secs == 0 && mins == 0 ) // time over
  {
    // disable the button
	  document.getElementById("btnCounter").disabled = true;
  }
  else{
	  timer = setTimeout(countDown, 1000);
	  if( --secs == -1 )
	  {
	  secs = 59;
	  --mins;
	  }
	    if( secs < 10 ) secs = "0" + secs;            
	    if( mins < 10 ) mins = "0" + parseInt( mins, 10 ); 
  }
}());
</script>
</html>
