

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginAction
 */
@WebServlet("/loginAction")
public class loginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginAction() {
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
		// TODO Auto-generated method stub
		 
	        PrintWriter out = response.getWriter();
	        String email = request.getParameter("email");
	        //Whatever was passed in the email ID will be stored in this string
	        String pass = request.getParameter("pass");
	        //Whatever was passed in the pass ID will be stored in this string
	        //----------------
	        //Looking into SQL database for email and passwork
			 //loading drivers for mysql
	        try {
				ArrayList<String> rsString = new ArrayList<String>();
	        	
	        	Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/car","root","password");
		        PreparedStatement ps = con.prepareStatement("select * from users where email=? and pass=?");
		        //Due to error I created my users table inside my "car" database, I chalk that up to inexperience, but in the future I'll keep 
		        //This in mind
		        //Also in said table I have a user called "testemail@yahoo.com" with a password of "password"
		        
		        ps.setString(1, email); //For the sake of this assignment the only accepted email will be testemail@yahoo.com
		        ps.setString(2, pass);	//and the only accepted password (when used in conjuction with above) is "password"
		        
		        ResultSet rs =ps.executeQuery();
		        boolean userCheck = false; //Exists to see if user is logged in or not
		        while (rs.next())
		        {
		        	
		        	String emailDatabase = rs.getString(1);
		        	String passDatabase = rs.getString(2);
		        	
		        	//out.println(emailDatabase + passDatabase);
		        	//System.out.println(emailDatabase + passDatabase);
		        	//The above two statements are just for checking if I did everything else right
		        	while (emailDatabase.equals(rs.getString(1)) && passDatabase.equals(rs.getString(2)))
			        {
		        		/*
		        		//------------------
			        	Cookie sessionCookie = new Cookie(emailDatabase, passDatabase);
			        	sessionCookie.setMaxAge(5000);
			        	response.addCookie(sessionCookie);
			        	//Set a cookie for the user if they are valid
			        	//--------------------
			        	//System.out.println(sessionCookie.getValue());//Print to the console just to check that I did this right
			        	
			        	//Upon using this I have a reserved token error, presumably because of the "@" symbol
			        	//Was considering converting the email to a string object or serializing but unsure of best practice
			        	
			        	 * 
			        	 */
		        		userCheck = true;
			        	break;
			        }
			        while (!emailDatabase.equals(rs.getString(1)) &&  !passDatabase.equals(rs.getString(2)))
			        {
			        	userCheck = false;
			        	break;
			        }

		        }
		        
		        if (userCheck == true)
		        {
		        	/*
		        	out.println("Welcome to the landing page, <br> this is a valid account");
		        	out.println("<br> head on back to the <a href=\"JavaServletTest/landingpage.html\"> index </a> ");
		        	*/ 
		        	//Above is old code, added a redirect instead
		        	response.sendRedirect("/JavaServletTest/landingpage.html");
		        	//If email and pass matches, go here
		        }
		        if (userCheck == false)
		        {
		        	out.println("Invalid User, redirecting...");

		        	response.sendRedirect("/JavaServletTest/loginretry.html");
		        	//If not, you'll be redirected to a new page to attempt to login again.

		        }
		        
		        		        
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        //creating connection with the database
	        
	}

}
