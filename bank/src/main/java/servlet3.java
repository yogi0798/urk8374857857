
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;

/**
 * Servlet implementation class servlet3
 */
@WebServlet("/servlet3")
public class servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	    }
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        String accname =request.getParameter("usename");
	        int password = Integer.parseInt(request.getParameter("password"));

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = (Connection) DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/bankmanagement","root","Yogi8925");

	            PreparedStatement ps = ((java.sql.Connection) con).prepareStatement(
	                "SELECT * FROM loginacc WHERE name = ? AND password = ?");
	            ps.setString(1,accname);
	            ps.setInt(2, password);
	            

	            ResultSet rs = ps.executeQuery();
	            
	            
	            if (rs.next()) {
	            RequestDispatcher rd=request.getRequestDispatcher("bankdashboard.html");
	            rd.forward(request, response);
	            } else {
	                out.println("<h2>Login Failed. Invalid username or password.</h2>");
	            }

	            con.close();
	        } catch (Exception e) {
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	        }
		

	}}


