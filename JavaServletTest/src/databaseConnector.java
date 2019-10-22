

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class databaseConnector
 */
@WebServlet("/databaseStuff")
public class databaseConnector extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public databaseConnector() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Small note to myself
		//I'm going to create this comment to help myself,I created a database called car with a table called Toyota
		//Inside the table is values for (string) Model, (String) Color, and (integer) yearCreated
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Set URL Username and Password
			String url= "jdbc:mysql://localhost:3306/car";   
			 String username="root";
			 String password="password";
			 try {
				Connection con = DriverManager.getConnection(url,username,password);
			    System.out.println("Connected to db");
			      Statement st = con.createStatement();
			      String sql = "select * from toyota"; //Set a statement to access all values from a table named "toyota"
			      //Information has been prefilled via mysql by me
			      //Screenshots should be provided to show what information is inside the table
			      
			      ResultSet rs = st.executeQuery(sql);
			      PrintWriter out = response.getWriter();
			      while(rs.next())
			      {
			    	  
			    	   String model = rs.getString(1);
			    	   String color = rs.getString(2);
			    	   int year = rs.getInt(3);
			    	   out.println("Model " + model + " Color:" + color +" Year : "+ year);
			    	   System.out.println(model + ":" + color +":"+ year);
			    	   
			      }
			      
			      response.getWriter().println("<a href=\"http://localhost:8080/JavaServletTest/index.html\"> index</a>"); //Send user back to index if they want
			      
			      
			      
			     out.close();
			  rs.close();
			  st.close();
			  con.close();
		 
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Establish driver
		finally {
			
		}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			
		}
		
		
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
