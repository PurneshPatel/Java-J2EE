package dao;

import static utils.HibernateUtils.getFactory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.AdharCard;
import pojos.Course;
import pojos.EducationalQualification;
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

	@Override
	public List<Student> getStudentsByCourse(String title) {
		List<Student> students=null;
	//	String jpql="select s from Student s where s.courseEnrolled.title=:title";// cross join
		String jpql="select s from Student s join fetch s.courseEnrolled c where c.title=:title";
		// get session from SF
		Session session=getFactory().getCurrentSession();
		//begin tx
		Transaction tx=session.beginTransaction();
		try {
			students=session.createQuery(jpql, Student.class).setParameter("title", title).getResultList();
		//	System.out.println(students.get(0).getCourseEnrolled());
			tx.commit();
		}catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return students;
	}

	@Override
	public Student getStudentDetails(int studentId) {
		Student s=null;
		// get session from SF
		Session session=getFactory().getCurrentSession();
		//begin tx
		Transaction tx=session.beginTransaction();
		try {
			s=session.get(Student.class, studentId);
			System.out.println(s.getCourseEnrolled().getCapacity());
			tx.commit();
		}catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return s;
	}

	@Override
	public String linkAdharCard(int studentId, AdharCard card) {
		String mesg="Linking adhar card failed...";
		// get session from SF
		Session session=getFactory().getCurrentSession();
		//begin tx
		Transaction tx=session.beginTransaction();
		try {
			//get student details by id
			Student s=session.get(Student.class, studentId);
			if(s != null)
			{
				//s : persistent
				s.setCard(card);
				mesg="Linked adhar card to Student "+s.getName();
			}
			tx.commit();
		}catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String linkOtherDetails(int studentId, List<String> hobbies, List<EducationalQualification> qualifications) {
		String mesg="Linking additional failed...";
		// get session from SF
		Session session=getFactory().getCurrentSession();
		//begin tx
		Transaction tx=session.beginTransaction();
		try {
			//get student details by id
			Student s=session.get(Student.class, studentId);
			if(s != null)
			{
				//s : persistent
				s.setHobbies(hobbies);
				s.setQualifications(qualifications);
				mesg="Linked details succeeded!";
			}
			tx.commit();
		}catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;

		
	}
	
	
	

}
