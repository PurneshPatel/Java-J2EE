package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Student;

public class DisplayStudentsByCourseTitle {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter course title");

			StudentDaoImpl dao = new StudentDaoImpl();
			dao.getStudentsByCourse(sc.next())
					.forEach(s -> System.out.println(s.getEmail() + " " + s.getCourseEnrolled().getTitle()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
