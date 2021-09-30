package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.User;
import org.hibernate.*;

@Repository // to tell SC : following class contains data access logic + enables exc
			// translation mechanism
public class UserDaoImpl implements IUserDao {
	// dependency : D.I
	@Autowired // byType
	private SessionFactory sf;

	@Override
	public User validateUser(String email, String pass) {
		String jpql="select u from User u where u.email=:em and u.password=:pass";
		
		return sf.getCurrentSession().
				createQuery(jpql, User.class).
				setParameter("em", email).
				setParameter("pass",pass).getSingleResult();
	}

}
