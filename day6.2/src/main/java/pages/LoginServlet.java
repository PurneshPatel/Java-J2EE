package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImpl;
import pojos.User;
import utils.DBUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/validate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			// dao inst
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			// to inform the WC that init() has failed --so don't continue with the
			// servicing : throw ServletExc
			// centralized exc handling in servlets
			throw new ServletException("err in init of " + getClass().getName(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();
			DBUtils.closeConnection();
		} catch (SQLException e) {
			System.out.println("err in destroy of " + getClass().getName() + " " + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-post of "+getClass().getName());
		// 1 : set cont type
		response.setContentType("text/html");
		// pw
		try (PrintWriter pw = response.getWriter()) {
			// read request params sent by the client
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			// Invoke dao's method : for user validation
			User user = userDao.validateUser(email, password);
			// chk for valid or invalid login
			if (user == null) {
				// invalid --retry link ---> login form
				pw.print("<h5>Invalid Login , Please  <a href='login.html'>Retry</a></h5>");
			} else {
				// send in the resp : validated user details
				pw.print("<h5>Login Successful from login servlet....</h5>");// does  appear on the clnt browser
				pw.flush();
				//save validated user details under min suitable scope (request) : used in server pull
				request.setAttribute("user_dtls", user);
				//RD steps
				//1. get RD object from WC
				RequestDispatcher rd=request.getRequestDispatcher("topics");
				//Forward the clnt to the next page in the SAME request
				rd.include(request, response);
				//WC : suspends exec of this method.  Invokes TopicsServlet's doPost
				//control comes back to Login Servlet n continues....
				System.out.println("control back in Login servlet....");
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post of " + getClass().getName(), e);
		}
	}

}
