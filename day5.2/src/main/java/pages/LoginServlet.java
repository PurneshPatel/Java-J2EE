package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TopicDaoImpl;
import dao.TutorialDaoImpl;
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
	private TopicDaoImpl topicDao;
	private TutorialDaoImpl tutDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			// dao inst
			userDao = new UserDaoImpl();
			topicDao=new TopicDaoImpl();
			tutDao=new TutorialDaoImpl();
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
			topicDao.cleanUp();
			tutDao.cleanUp();
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
				// 1. request WC to get session
				HttpSession session = request.getSession();
				System.out.println("session : "+session.getClass().getName());
				System.out.println("from login page : " + session.isNew());// true
				System.out.println("Session ID " + session.getId());
				// add validated user details under the session scope
				session.setAttribute("user_details", user);
				//add topic dao instance under the session scope(so that other servlets can share the same.)
				session.setAttribute("topic_dao", topicDao);
				//add tut dao instance under the session scope(so that other servlets can share the same.)
				session.setAttribute("tut_dao", tutDao);
				// in case of successful login : redirect the clnt to the topics page
				// API : HttpServletResponse
				// public void sendRedirect(String location) throws IOException
				response.sendRedirect("topics");// prog
				// WC : sends temp redirect resp to the clnt
				// sts code : SC302 | location=topics , content-length=0,Set-cookie : JSESSIONID
				// : string val generated by WC , unique per clnt
				// | body : EMPTY
				// clnt browser : generate a NEW request
				// URL : http://host:port/day5.1/topics , method=GET
				// request header : cookie : JSESSIONID : some unq value
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post of " + getClass().getName(), e);
		}
	}

}
