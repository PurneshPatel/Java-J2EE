package dao;

import java.sql.SQLException;
import java.util.List;

public interface ITutorialDao {
	//add a method to ret list of tut names for a chosen topic
	List<String> getAllTutorialsByTopic(String topicName) throws SQLException;
}
