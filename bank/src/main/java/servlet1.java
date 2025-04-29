

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

import org.apache.catalina.connector.Response;

import com.sun.jdi.connect.spi.Connection;

import bank.connect;

/**
 
 */
@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet1() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		doGet(request, response);
		 String name = request.getParameter("fname");
	        int age = Integer.parseInt(request.getParameter("fage"));
	        String dob = request.getParameter("fdob");
	        long aadhar = Long.parseLong(request.getParameter("fad"));
	        
	        // Database connection and insertion
	        try {
	          connect m=new connect();
	          Classforname("com.mysql.cj.jdbc.driver");
	        int l=  m.insert(name,age,dob,aadhar);
	        if(l==1) {
	        	response.getWriter().println("added");
	        	RequestDispatcher rd=request.getRequestDispatcher("accno.html");
	        	
	        	rd.forward(request, response);
	        	
	        	}
	       else {
	        	response.getWriter().println("no added");
	        	
	        }
	        
	        
	       
	            
	            
	        } catch(Exception e) {
	            response.getWriter().println("Error: " + e.getMessage());
	            e.printStackTrace();
	        }


			
		
	    }

	private void Classforname(String string) {
		
		
	}
	

		
	}


