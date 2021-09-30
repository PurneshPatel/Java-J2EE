package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojos.User;

class TestUserDaoImpl {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testValidateUser() {
		UserDaoImpl dao=new UserDaoImpl();
		User user = dao.validateUser("sam@gmail.com", "2233");
		System.out.println(user);
		assertEquals("Sameer", user.getName());
	}

}
