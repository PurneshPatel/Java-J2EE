package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TopicDaoImplTest {
	private TopicDaoImpl topicDao;

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("in set up");
		topicDao=new TopicDaoImpl();
	}

	@Test
	void testGetAllTopicNames() throws Exception{
		List<String> allTopicNames = topicDao.getAllTopicNames();
		System.out.println(allTopicNames);
		//expected , actual
		assertEquals(33,allTopicNames.size());
	}

}
