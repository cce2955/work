

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
import javax.servlet.http.HttpSession;

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
		  	
		HttpSession currentSession = request.getSession();
		int sessionIndex = 0;
		//--------------------------------------------
		if(currentSession.isNew()) //Send them to the login page
		{
			PrintWriter write = response.getWriter();
			System.out.println("welcome");
			write.append("Welcome");
			
		}
		else if(!currentSession.isNew()) 
		{
			PrintWriter write = response.getWriter();
			write.append ("you refreshed!!");
		}
			//--------------------------------------------
		//I created a version to detect if a session is new or not, but I suspect as this servlet's only fucking is to calculate and then immediate change pages that it won't actually work
		//and the servlet will create a new session as its loaded, as I'm running out of time I'm going to leave this as is.
		
		
			System.out.println(sessionIndex);
	        PrintWriter out = response.getWriter();
	        String email = request.getParameter("email");
	        //Whatever was passed in the email ID will be stored in this string
	        String pass = request.getParameter("pass");
	        //Whatever was passed in the pass ID will be stored in this string
	        
	        
	        
	        //A quick workaround a banned token in java, user will enter the entire string and I'll jerry rig a solution to omit the @ while keeping the email and domain together
	        //----------------
	        //Looking into SQL database for email and passwords
			 //loading drivers for mysql
	        //In my example data base the only accepted passwords are
			//	        +---------------+-------------+-------------------+
			//	        | email         | domain      | password          |
			//	        +---------------+-------------+-------------------+
			//	        | testemail     | yahoo.com   | password          |
			//	        | nlstraining   | gmail.com   | password          |
			//	        | chris.everett | yahoo.com   | password          |
			//	        | newuser       | hotmail.com | alternatepassword |
			//	        |---+---------------+---------+-------------------+
	        try {
				ArrayList<String> rsString = new ArrayList<String>();
	        	
	        	Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","password"); //Connect to database named "Users"
		        PreparedStatement ps = con.prepareStatement("select * from activeusers where email=? and domain=? and password=?");
		        //Changed databases, now have an actual database called users, with a table called activeusers
		        
		        //-------------------------
		        //Safe to ignore this comment, I'm keeping it for legacy
		        /*
		        //Due to error I created my users table inside my "car" database, I chalk that up to inexperience, but in the future I'll keep 
		        //This in mind
		        //Also in said table I have a user called "testemail@yahoo.com" with a password of "password"
		        */
		        //-----------------------------
		        
		        
		        
		        //I'm going to do a little string building to determine the domain name
		        StringBuilder emailDeconstructor = new StringBuilder();
		        StringBuilder domainConstructor = new StringBuilder();
		        
		        emailDeconstructor.append(email); //Convert email to a string builder
		        domainConstructor.append(email); //Convert email to a string builder
		        int variableLength = emailDeconstructor.length(); //Used for the for loop below, gonna do some risky length changing below
		        for (int i = 0; i < variableLength; i++)
		        {
		        	
		        	if (emailDeconstructor.charAt(i) == '@')
		        	{
		        		
		        		
		        		emailDeconstructor.delete(i, variableLength);//As the for loop reaches the @ of our input, it'll delete @ and everything after, leaving us "email@"
		        		domainConstructor.delete(0, i + 1);//Exact opposite, delete @ and everything before, leaving us with "@domain "
		        		i = variableLength;//Set i at the max length so get out of the for loop
//		        		System.out.println(emailDeconstructor);
//		        		System.out.println(domainConstructor);
//		        		
		        		
		        		
		        	}
		        }
		        	
		        //---------------------------------------	
		        String domain = domainConstructor.toString(); 
		        String emailSubmit = emailDeconstructor.toString(); 
		        //All the above code produced a domain to store inside the domain string, which will be set to the prepared statement below to check against the database
		        //And an email as well
		       
		        ps.setString(1, emailSubmit);//See if the user submitted email matches up with anything in the database
		        ps.setString(2, domain);//see if the domain matches with if the email
		        ps.setString(3, pass);	//And see if the password matches
		        
		        ResultSet rs =ps.executeQuery();
		        //Check if anything in the database matches the above 3 variables
		        
	        	

		        
		        boolean userCheck = false; //Exists to see if user is logged in or not
		        while (rs.next())
		        {
		        	
		        	String emailDatabase = rs.getString(1);
		        	String domainDatabase = rs.getString(2);
		        	String passDatabase = rs.getString(3);
		        	
		        	 
		        	while (emailDatabase.equals(emailSubmit) && domainDatabase.equals(domain) && passDatabase.equals(pass))
			        {
		        		Cookie sessionCookie = new Cookie(emailDatabase, passDatabase);
			        	sessionCookie.setMaxAge(5000);
			        	response.addCookie(sessionCookie);
			        	
			        	//Set a cookie for the user if they are valid
			        	//--------------------
			        	//Set user as true to send them to the landing page
		        		userCheck = true;
			        	break;
			        }
			        while (!emailDatabase.equals(emailSubmit) &&  !domainDatabase.equals(domain) && !passDatabase.equals(pass))
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
		        	request.getRequestDispatcher("/loginretry.html").forward(request, response);
		        	
		        	//response.sendRedirect("/JavaServletTest/loginretry.html");
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
	
	

