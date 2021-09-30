package dao;

import pojos.User;
import static utils.HibernateUtils.getFactory;

import java.io.Serializable;

import org.hibernate.*;

public class UserDaoImpl implements IUserDao {
	// NO data members , no constr , no cleanup

	@Override
	public String registerUser(User user) {
		String mesg = "User reg failed....";
		// user : state : TRANSIENT(not yet persistent)
		// get the Sesssion from SF
		Session session = getFactory().openSession();
		Session session2 = getFactory().openSession();
		System.out.println(session == session2);// false
		// begin a tx
		Transaction tx = session.beginTransaction();// db cn is pooled out --wrapped in Session obj, L1 cache created
		System.out.println("session is open " + session.isOpen() + " is connected to db cn " + session.isConnected());// t
																														// t
		try {
			// org.hibernate.Session API : public Serializable save(Object transientObjRef)
			// throws HibernateExc
			Serializable userId = session.save(user);// user ref is added in the cache : PERSISTENT
			tx.commit();// Upon commit : Hibernate performs "auto dirty checking" :by comparing state of
						// L1 cache with that of DB : DML will be fired (insert)
			mesg = "user registered successfully with ID=" + userId;
			System.out
					.println("session is open " + session.isOpen() + " is connected to db cn " + session.isConnected());// t
																														// t
			// user : PERSISTENT
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			if (session != null)
				session.close();// pooled out DB cn rets to the pool n L1 cache is destroyed
			if (session2 != null)
				session2.close();
		}
		// user : DETACHED
		System.out.println("session is open " + session.isOpen() + " is connected to db cn " + session.isConnected());// f
																														// f

		return mesg;
	} // user : DETACHED

	@Override
	public String registerUserWithGetCurrentSession(User user) {
		String mesg = "User reg failed....";
		// user : state : TRANSIENT
		// get the Sesssion from SF
		Session session = getFactory().getCurrentSession();
		Session session2 = getFactory().getCurrentSession();
		System.out.println(session == session2);// true
		// begin a tx
		Transaction tx = session.beginTransaction();// db cn is pooled out --wrapped in Session obj, L1 cache created
		System.out.println("session is open " + session.isOpen() + " is connected to db cn " + session.isConnected());// t
																														// t
		try {
			// org.hibernate.Session API : public Serializable save(Object transientObjRef)
			// throws HibernateExc
			Serializable userId = session.save(user);// user ref is added in the cache : PERSISTENT
			tx.commit();// Upon commit : Hibernate performs "auto dirty checking" :by comparing state of
						// L1 cache with that of DB : DML will be fired (insert)
			// pooled out DB cn rets to the pool n L1 cache is destroyed
			mesg = "user registered successfully with ID=" + userId;
			System.out
					.println("session is open " + session.isOpen() + " is connected to db cn " + session.isConnected());// f
																														// f

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();// Upon rollback : Hibernate DOES NOT performs "auto dirty checking"
// pooled out DB cn rets to the pool n L1 cache is destroyed
			throw e;
		}
		System.out.println("session is open " + session.isOpen() + " is connected to db cn " + session.isConnected());// f
																														// f

		return mesg;
	}

	@Override
	public User getUserDetails(int userId) {
		User user=null;// user : does not exist
		// get Session from SF : getCurrentSession
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// L1 cache : empty
			// get
			user = session.get(User.class, userId);// int ---> Integer --> Serializable : user
			user = session.get(User.class, userId);// int ---> Integer --> Serializable
			user = session.get(User.class, userId);// int ---> Integer --> Serializable
			// select * from users_tbl where user_id=?
			// invalid user id : rets null
			// valid user id : de-serial(re -constructing user object from db data) : user :
			// PERSISTENT
			tx.commit();// auto dirty chking(since no difference between L1 cache n db : NO DML), db cn
						// rets to the pool, L1 cache is destroyed.
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
	
		return user;//user : DETACHED
	}

}
