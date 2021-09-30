package tester;
import static java.time.LocalDate.parse;
import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.CourseDaoImpl;
import pojos.Course;

public class LaunchNewCourse {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();Scanner sc=new Scanner(System.in))
		{
			System.out.println("Enter Course Details :  title,  startDate,  endDate,  fees,  capacity");
			Course c=new Course(sc.next(), parse(sc.next()), parse(sc.next()),sc.nextDouble(), sc.nextInt());
			CourseDaoImpl dao=new CourseDaoImpl();
			//c : transient
			System.out.println(dao.launchNewCourse(c));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
