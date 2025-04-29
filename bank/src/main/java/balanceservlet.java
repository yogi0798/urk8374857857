

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class balanceservlet
 */
@WebServlet("/balanceservlet")
public class balanceservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public balanceservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement", "root", "Yogi8925");

	        long accnos = Long.parseLong(request.getParameter("accnum"));
	        long accno = Long.parseLong(request.getParameter("accnum2"));
	       

	        if (accnos != accno) {
	            out.println("<h3 style='color:red'>Account numbers do not match!</h3>");
	            return;
	        }

	        String sql = "SELECT minimumbal FROM loginacc WHERE accno = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setLong(1, accno);
	        ResultSet rs = pst.executeQuery();

	        int min1dbalance = 0;
	        if (rs.next()) {
	            min1dbalance = rs.getInt("minimumbal");
	            out.println("<h3 style='color:green'>current balance: " +   min1dbalance  + "</h3>");
	        } else {
	            out.println("<h3 style='color:red'>Account not found!</h3>");
	            return;
	        }}catch (Exception e) {
		        out.println("<h3 style='color:red'>Error: " + e.getMessage() + "</h3>");
		        e.printStackTrace(out);
		    }

	    
	}}

