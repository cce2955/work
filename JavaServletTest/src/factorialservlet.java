

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class factorialservlet
 */
@WebServlet("/factorialservlet")
public class factorialservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public factorialservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Short calculation to create a factorial sum of whatever the user has input
		PrintWriter out = response.getWriter();
		int x = 0;
		String factSum = request.getParameter("factNum");
		int minFactSum = Integer.valueOf(factSum); //Shows the initial value the user has input
		int intFactSum = Integer.valueOf(factSum); //Will be used in the loop below to create a factorial sum
		for (int i = 5 ; i > 0; i--)
		{
			x = i - 1;
			if (i == 0 || i == 1)
			{
				x = 1;
			}
			intFactSum = intFactSum * x;
			//Quick program to calculate factorial of 5, kind of works for any other number you put in too I guess
		}
		out.append("The factorial value of " + minFactSum + " is " + intFactSum);
		out.append("<br> Return to the <a href=\"/JavaServletTest/landingpage.html\">the main page </a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
