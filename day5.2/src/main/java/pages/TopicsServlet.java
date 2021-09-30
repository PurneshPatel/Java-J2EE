package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TopicDaoImpl;
import pojos.User;

/**
 * Servlet implementation class TopicsServlet
 */
@WebServlet("/topics")
public class TopicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>Login Successful from topic  servlet....</h5>");

			// 1. get HttpSession from WC
			HttpSession hs = request.getSession();// rets EXISTING HS object to the caller.
			System.out.println("from topics page " + hs.isNew());// false (provided cookies are enabled!)
			System.out.println("Session ID " + hs.getId());// SAME for the same clnt
			// get validated user details from a HttpSession
			User user = (User) hs.getAttribute("user_details");
			if (user != null) {// session tracking : works!
				pw.print("<h5>Validated User Details from Session " + user + "</h5>");
				// get topic dao instance from the HttpSession
				TopicDaoImpl dao = (TopicDaoImpl) hs.getAttribute("topic_dao");
				// invoke topic dao's method to get all topic names
				List<String> allTopicNames = dao.getAllTopicNames();
				// allTopicNames.forEach(topicName -> pw.print(topicName+"<br/>"));
				// dynamic form generation
				pw.print("<form action='tutorials'>");
				pw.print("<h5 align='center'>All Topics</h5>");
				allTopicNames.forEach(topicName -> pw
						.print("<input type='radio' name='topic' value='" + topicName + "'/>" + topicName+"<br/>"));
				pw.print("<input type='submit' value='Choose Topic'/>");
				pw.print("</form>");
			} else // no cookies : no session tracking!
				pw.print("<h5>No Cookies : session tracking failed!!!!!!!</h5>");
			// add a logout link
			pw.print("<h5> <a href='logout'>Log Out</a></h5>");

		} catch (Exception e) {
			throw new ServletException("err in do-get of " + getClass().getName(), e);
		}
	}

}
