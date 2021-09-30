package dao;

import static utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Course;

public class CourseDaoImpl implements ICourseDao {

	@Override
	public String launchNewCourse(Course course) {
		String mesg="launching new course failed!!!!!";
		// get session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			//course : TRANSIENT
			session.persist(course); //PERSISTENT
			tx.commit();//insert query
			mesg="Launched Course "+course.getTitle()+" with ID="+course.getId();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}
