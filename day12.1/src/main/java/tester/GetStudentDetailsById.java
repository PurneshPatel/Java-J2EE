package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Student;

public class GetStudentDetailsById {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter student id");

			StudentDaoImpl dao = new StudentDaoImpl();
			Student student = dao.getStudentDetails(sc.nextInt());
			System.out.println("Email " + student.getEmail() + " Name " + student.getEmail());
			System.out.println("Enrolled course details");
			// org.hibernate.LazyInitializationException : You are trying to access un
			// fetched data(represented by un-inited proxy) outside the session scope
			System.out.println(student.getCourseEnrolled());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
