package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Tutorial;

import static utils.DBUtils.fetchDBConnection;

public class TutorialDaoImpl implements ITutorialDao {
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3, pst4;

	public TutorialDaoImpl() throws ClassNotFoundException, SQLException {
		// get cn
		cn = fetchDBConnection();
		// pst1 : join query to fetch tut names for a specific topic
		pst1 = cn.prepareStatement(
				"select t1.name from tutorials t1 inner join topics t2 on t1.topic_id=t2.id where t2.name=? order by visits desc");
		// pst2 : get tut details by it's name
		pst2 = cn.prepareStatement("select * from tutorials where name=?");
		// pst3 : update visits
		pst3 = cn.prepareStatement("update tutorials set visits=visits+1 where name=?");
		// pst4 :insert tut record
		pst4 = cn.prepareStatement("insert into tutorials values(default,?,?,?,?,?,?)");
		System.out.println("tut dao created...");

	}

	@Override
	public List<String> getAllTutorialsByTopic(String topicName) throws SQLException {
		List<String> tutNames = new ArrayList<>();
		// set IN param : topic name
		pst1.setString(1, topicName);
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				tutNames.add(rst.getString(1));
		}
		return tutNames;
	}

	@Override
	public Tutorial getTutorialDetails(String tutName) throws SQLException {
		// set In param : tut name
		pst2.setString(1, tutName);
		try (ResultSet rst = pst2.executeQuery()) {
			if (rst.next())
				// int tutorialId, String tutorialName, String author, Date publishDate, int
				// visits, int topicId,String contents) {

				return new Tutorial(rst.getInt(1), tutName, rst.getString(3), rst.getDate(4), rst.getInt(5),
						rst.getInt(6), rst.getString(7));
		}
		return null;
	}

	@Override
	public String updateVisits(String tutName) throws SQLException {
		// set IN param
		pst3.setString(1, tutName);
		int updateCount = pst3.executeUpdate();
		if (updateCount == 1)
			return "Visits updated";
		return "Visits updation failed...";
	}

	@Override
	public String addNewTutorial(Tutorial tutorial) throws SQLException {
		// name | author | publish_date | visits | topic_id | contents
		// set IN params
		pst4.setString(1, tutorial.getTutorialName());
		pst4.setString(2, tutorial.getAuthor());
		pst4.setDate(3, tutorial.getPublishDate());
		pst4.setInt(4, tutorial.getVisits());
		pst4.setInt(5, tutorial.getTopicId());
		pst4.setString(6, tutorial.getContents());
		int updateCount = pst4.executeUpdate();
		if (updateCount == 1)
			return "Inserted new tutorial";
		return "Inserting new tutorial failed...";

	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		if (pst4 != null)
			pst4.close();
		System.out.println("tut dao cleaned up");
	}

}
