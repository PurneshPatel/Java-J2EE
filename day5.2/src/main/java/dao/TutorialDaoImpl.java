package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.fetchDBConnection;

public class TutorialDaoImpl implements ITutorialDao {
	private Connection cn;
	private PreparedStatement pst1;

	public TutorialDaoImpl() throws ClassNotFoundException,SQLException{
		// get cn
		cn=fetchDBConnection();
		//pst1
		pst1=cn.prepareStatement("select t1.name from tutorials t1 inner join topics t2 on t1.topic_id=t2.id where t2.name=?");
	}
	

	@Override
	public List<String> getAllTutorialsByTopic(String topicName) throws SQLException {
		List<String> tutNames=new ArrayList<>();
		//set IN param : topic name
		pst1.setString(1, topicName);
		try(ResultSet rst=pst1.executeQuery())
		{
			while(rst.next())
				tutNames.add(rst.getString(1));
		}
		return tutNames;
	}
	public void cleanUp() throws SQLException
	{
		if(pst1 != null)
			pst1.close();
		System.out.println("tut dao cleaned up");
	}

}
