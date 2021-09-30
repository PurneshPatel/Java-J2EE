package beans;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import dao.TopicDaoImpl;
import dao.TutorialDaoImpl;
import pojos.Topic;
import pojos.Tutorial;

public class TopicTutorialBean {
//properties
	private TopicDaoImpl topicDao;
	private TutorialDaoImpl tutDao;

	/*
	 * topicId=2&tutName=JSP+EL+Syntax&tutAuthor=Riya&pubDate=2021-08-17&visits=123&
	 * contents=fghsgf
	 */
	private int topicId;
	private String tutName;
	private String tutAuthor;
	private String pubDate;
	private int visits;
	private String contents;
	private String message;

	public TopicTutorialBean() throws ClassNotFoundException, SQLException {
		System.out.println("in ctor of " + getClass().getName());
		// dao instance
		topicDao = new TopicDaoImpl();
		tutDao = new TutorialDaoImpl();
	}

	// setters n getters
	public TopicDaoImpl getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDaoImpl topicDao) {
		this.topicDao = topicDao;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTutName() {
		return tutName;
	}

	public void setTutName(String tutName) {
		this.tutName = tutName;
	}

	public String getTutAuthor() {
		return tutAuthor;
	}

	public void setTutAuthor(String tutAuthor) {
		this.tutAuthor = tutAuthor;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TutorialDaoImpl getTutDao() {
		return tutDao;
	}

	public void setTutDao(TutorialDaoImpl tutDao) {
		this.tutDao = tutDao;
	}

	// Add B.L method to fetch all topics
	public List<Topic> getTopics() throws SQLException {
		return topicDao.getAllTopics();
	}

	// Add B.L method to validate n add new tutorial
	public String validateInsertTutorialDetails() throws SQLException{
		System.out.println("B.L : validation + ins");
		LocalDate publishDate = LocalDate.parse(pubDate);//string--->LocalDate
		// API of java.time.Period
		int periodInMonths =(int) Period.between(publishDate, LocalDate.now()).toTotalMonths();
		if (contents.length() >= 255 || periodInMonths > 6) {
			// validation failed!!!!!
			message = "Adding new tutorial failed : Invalid date or contents";
			return "show_tut_form";
		}
		// validation success
		// invoke dao layer method to insert tut details
		//public Tutorial(String tutorialName, String author, Date publishDate, int visits, String contents, int topicId) {

		message=tutDao.addNewTutorial(new Tutorial(tutName, tutAuthor, Date.valueOf(publishDate), visits,  contents,topicId));
		if(message.contains("failed"))
			return "show_tut_form";
		return "logout";
	}
}
