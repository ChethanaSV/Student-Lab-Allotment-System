import java.io.*;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
@WebServlet(urlPatterns = "/txt")
public class TextServlet extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"example.txt\"");
        try {
            OutputStream outputStream = response.getOutputStream();
            String outputResult = "This is Test";
            outputStream.write(outputResult.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}