package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TutorialDaoImplTest {
	private TutorialDaoImpl dao;

	@BeforeEach
	void setUp() throws Exception {
		dao=new TutorialDaoImpl();
	}

	@Test
	void testGetAllTutorialsByTopic() throws Exception {
		List<String> allTutorialsByTopic = dao.getAllTutorialsByTopic("Spring Framework");
		System.out.println(allTutorialsByTopic);
		assertEquals(true, allTutorialsByTopic.contains("Spring MVC"));
		
	}

}
