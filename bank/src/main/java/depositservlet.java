

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
import java.sql.SQLException;

import bank.bankaccount;

/**
 * Servlet implementation class depositservlet
 */
@WebServlet("/depositservlet")
public class depositservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public depositservlet() {
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
	
		 response.setContentType("text/html");
		    PrintWriter out = response.getWriter();

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement", "root", "Yogi8925");

		        long accnos = Long.parseLong(request.getParameter("accnum"));
		        long accno = Long.parseLong(request.getParameter("accnum2"));
		        int damount = Integer.parseInt(request.getParameter("damount"));
		      
				String type="deposit";

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
		        } else {
		            out.println("<h3 style='color:red'>Account not found!</h3>");
		            return;
		        }

		        int newBalance = min1dbalance + damount;
		        PreparedStatement psr = con.prepareStatement("UPDATE loginacc SET minimumbal  = ? WHERE accno = ?");
		        psr.setInt(1, newBalance);
		        psr.setLong(2, accno);
		        psr.executeUpdate();

		        out.println("<h3 style='color:green'>Deposit successful. New Balance: " + newBalance + "</h3>");
		      
		        String tableName = "transactions_" + accnos; // example: transactions_8930615997
		        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
		                + "id INT AUTO_INCREMENT PRIMARY KEY, "
		                + "accountNumber BIGINT, "
		                + "type VARCHAR(20), "
		                + "amount INT, "
		                + "balance INT, "
		                + "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
		                + ")";
		        PreparedStatement createTablePs = con.prepareStatement(createTableSQL);
		        createTablePs.executeUpdate();
		        String insertSQL = "INSERT INTO " + tableName + "(accountNumber, type, amount, balance, timestamp) VALUES (?, ?, ?, ?, NOW())";
		        PreparedStatement ps = con.prepareStatement(insertSQL);
		        ps.setLong(1, accnos);
		        ps.setString(2, type);
		        ps.setInt(3, damount);
		        ps.setInt(4, newBalance);
		        ps.executeUpdate();

		    } catch (Exception e) {
		        out.println("<h3 style='color:red'>Error: " + e.getMessage() + "</h3>");
		        e.printStackTrace(out);
		    }
		}

		
	}


