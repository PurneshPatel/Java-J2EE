package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojos.User;

class TestUserDaoImpl {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		UserDaoImpl dao=new UserDaoImpl();
		User user = dao.getUserDetails(4);
		System.out.println(user);
		assertEquals("Sameer", user.getName());
	}

}
