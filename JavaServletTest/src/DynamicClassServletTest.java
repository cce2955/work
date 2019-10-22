

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.net.URL;
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
 * Servlet implementation class DynamicClassServletTest
 */
@WebServlet("/DynamicClassServletTest")

interface table
{
	public void tableStart(int x, int y);
	
}




public class DynamicClassServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DynamicClassServletTest() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 PrintWriter out = response.getWriter();
		//response.getWriter().print("Hello, world \n");
		for (int i = 0 ; i < 10; i++)
		{
			response.getWriter().println(" Hello World");
		
			
		}
		//Anyway,above is printing out hello world 10 times via my established servlet
		
		table multiTable = (x, y)->{
			try {
				response.getWriter().print(x * y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}; //Doing some lambda practice, this is just a simple multiplication method that prints out whatever value I toss in
		for (int i = 0 ; i < 10; i++)
		{
			response.getWriter().println("");
			multiTable.tableStart(2, i);
			
			//Print out a multiplication table, this cycles through the times table of 2
			//Replace with any number to make a new table, or copy the interface to make multiple tables
		}
		response.getWriter().println("");
		response.getWriter().println("");
		int x = 0;
		int factSum = 5;
		for (int i = 5 ; i > 0; i--)
		{
			x = i - 1;
			if (i == 0 || i == 1)
			{
				x = 1;
			}
			factSum = factSum * x;
			//Quick program to calculate factorial of 5, kind of works for any other number you put in too I guess
		}
		response.getWriter().println(factSum);
		response.getWriter().println("");
		response.getWriter().println("");
		for (int i = 100; i >= 10; i--)
		{
			response.getWriter().println(i);
			
			//Program that prints 100 to 10 descending
		}
		}
		
		finally {
			
		}
		
		
		
		
		response.getWriter().println("<a href=\"http://localhost:8080/JavaServletTest/index.html\"> index</a>"); //Send user back to index if they want
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
