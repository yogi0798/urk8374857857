

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import bank.connect;

/**
 * Servlet implementation class servelt2
 */
@WebServlet("/servelt2")
public class servelt2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servelt2() {
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

	    try {
	        // Get user ID from request parameter
	        int userId = Integer.parseInt(request.getParameter("pin"));
	        String ame = request.getParameter("hname");
	        int minimumbalance=Integer.parseInt(request.getParameter("mbal"));
	      

	     
	        connect ms = new connect();
	        long accnos = ms.accno(userId,ame,minimumbalance);

	       
	        out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<style>");
	        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
	        out.println(".container { background: white; padding: 20px; border-radius: 10px; max-width: 400px; margin: auto; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
	        out.println("h2 { color: green; }");
	        out.println(".account-number { font-size: 20px; font-weight: bold; color: #333; }");
	        out.println("</style>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class='container'>");
	        out.println("<h2>Account Created Successfully</h2>");
	        out.println("<h4>"+ame+"</h4>");
	        out.println("<p class='account-number'>Your Account Number: " + accnos + "</p>");
	       
	        out.println("<a href='login.html' style='display:inline-block; margin-top:15px; padding:10px 20px; background-color:#007bff; color:#fff; text-decoration:none; border-radius:5px;'>Go to Login</a>");
	        out.println("</div>");
	        out.println("</body>");
	        out.println("</html>");
	    } catch (Exception e) {
	        out.println("<p>Error generating account: " + e.getMessage() + "</p>");
	    }
	}

	}


