package tester;
import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Student;

public class StudentAdmission {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();Scanner sc=new Scanner(System.in))
		{
			System.out.println("Enter student details : name email");
			Student student=new Student(sc.next(), sc.next());
			System.out.println("Enter course title");
			StudentDaoImpl dao=new StudentDaoImpl();
			System.out.println(dao.admitNewStudent(student, sc.next()));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
