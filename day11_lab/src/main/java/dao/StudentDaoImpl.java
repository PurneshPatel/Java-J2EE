package dao;

import static utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Course;
import pojos.Student;

public class StudentDaoImpl implements IStudentDao {

	@Override
	public String admitNewStudent(Student student, String courseTitle) {
		String jpql = "select c from Course c where c.title=:title";
		String mesg = "Student admission failed!!!!";
		// get session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			Course course = session.createQuery(jpql, Course.class).setParameter("title", courseTitle)
					.getSingleResult();
			// => course : PERSISTENT , student : TRANSIENT : till now no link between
			// entities
			// establish the link from student --> course (setter)
			student.setCourseEnrolled(course);// uni dir link
			session.persist(student);
			tx.commit();
			mesg = "Student " + student.getName() + " got enrolled in course " + courseTitle + " roll no "
					+ student.getId();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}
