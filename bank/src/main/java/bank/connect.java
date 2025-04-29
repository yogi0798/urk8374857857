package bank;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;



import java.sql.Connection ;


public class connect {
	Connection con;
	
	public connect()throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement","root","Yogi8925");
		System.out.println("connected");
		
	}
	public int insert(String name,int age,String dob,long add) throws SQLException {
		PreparedStatement ps = con.prepareStatement("INSERT INTO registration VALUES(?,?,?,?)");
	    ps.setString(1, name);
	    ps.setInt(2, age);
	    ps.setString(3, dob);
	    ps.setLong(4, add);
	   int r=ps.executeUpdate();
	  
	    con.close();
	    return r;

	}
	
	public long accno(int l,String named,int num) throws SQLException,Exception {
		
	    Random random = new Random();
	    long accno = 1000000000L + (long)(random.nextDouble() * 9000000000L);
	    System.out.println(accno);
	   
	    PreparedStatement ps=con.prepareStatement("insert into  loginacc values(?,?,?,?)");
		ps.setLong(1,accno);
		ps.setInt(2,l);
		ps.setString(3,named);
		ps.setInt(4,num);
		
		
		int r=ps.executeUpdate();
		System.out.print(r==1?"sucessfully pin generated":"pls try again");
	
	
	    
	    return accno;
	}
	

		


	public static void main(String[] args) throws SQLException {
		try {
			connect m=new connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
