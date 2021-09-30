package dao;

import static utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.User;

public class UserDaoImpl implements IUserDao {
	// NO data members , no constr , no cleanup
	@Override
	public User validateUser(String email, String password) {
		User user = null;
		String jpql = "select u from User u where u.email=:email and u.password=:pass";
		// get session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			user = session.createQuery(jpql, User.class).setParameter("email", email).
					setParameter("pass", password)
					.getSingleResult();
			//user : persistent
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return user;//user : detached
	}

}
