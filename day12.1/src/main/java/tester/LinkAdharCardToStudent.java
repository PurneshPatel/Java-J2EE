package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.AdharCard;
import pojos.Student;

public class LinkAdharCardToStudent {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter student id");
			int sid = sc.nextInt();
			System.out.println("Enter card details : cardNumber,  createdOn,  location");
			StudentDaoImpl dao = new StudentDaoImpl();
			System.out.println(dao.linkAdharCard(sid, 
					new AdharCard(sc.next(), LocalDate.parse(sc.next()), sc.next())));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
