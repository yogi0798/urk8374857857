

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class transactionhistroy
 */
@WebServlet("/transactionhistroy")
public class transactionhistroy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transactionhistroy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        Long accountNumber = Long.parseLong(request.getParameter("accnum")); // Fetch from URL ?accountNumber=8930615997
	        String tableName = "transactions_" + accountNumber;

	        out.println("<html><head><title>Transaction History</title>");
	        out.println("<style>");
	        out.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; }");
	        out.println("h2 { text-align: center; color: #333; }");
	        out.println("table { margin: auto; border-collapse: collapse; width: 80%; background-color: #fff; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }");
	        out.println("th, td { padding: 12px; border: 1px solid #ddd; text-align: center; }");
	        out.println("th { background-color: #4CAF50; color: white; }");
	        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
	        out.println("</style>");
	        out.println("</head><body>");

	        out.println("<h2>Transaction History for Account: " + accountNumber + "</h2>");

	        try {
	            // Database connection
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/bankmanagement","root","Yogi8925");

	            // Query to select records from table where account number matches
	            String sql = "SELECT * FROM " + tableName + " WHERE accountNumber = ? ORDER BY timestamp DESC";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setLong(1,accountNumber);
	            ResultSet rs = ps.executeQuery();

	            out.println("<table>");
	            out.println("<tr><th>ID</th><th>Account Number</th><th>Type</th><th>Amount</th><th>Balance</th><th>Timestamp</th></tr>");

	            while (rs.next()) {
	                out.println("<tr>");
	                out.println("<td>" + rs.getInt("id") + "</td>");
	                out.println("<td>" + rs.getLong("accountNumber") + "</td>");
	                out.println("<td>" + rs.getString("type") + "</td>");
	                out.println("<td>" + rs.getInt("amount") + "</td>");
	                out.println("<td>" + rs.getInt("balance") + "</td>");
	                out.println("<td>" + rs.getTimestamp("timestamp") + "</td>");
	                out.println("</tr>");
	            }

	            out.println("</table>");

	            rs.close();
	            ps.close();
	            con.close();

	        } catch (Exception e) {
	            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
	            e.printStackTrace(out);
	        }

	        out.println("</body></html>");
	    }
	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
