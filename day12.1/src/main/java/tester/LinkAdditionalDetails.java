package tester;

import static utils.HibernateUtils.getFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.EduType;
import pojos.EducationalQualification;

public class LinkAdditionalDetails {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter student id");
			int sid = sc.nextInt();
			System.out.println("Enter 2 hobbies");
			List<String> myHobbies = Arrays.asList(sc.next(), sc.next());
			List<EducationalQualification> quals = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				System.out.println("Enter edu quals :  type,  year,  marks");
				quals.add(new EducationalQualification(EduType.valueOf(sc.next().toUpperCase()), sc.nextInt(),
						sc.nextDouble()));

			}
			StudentDaoImpl dao = new StudentDaoImpl();
			System.out.println(dao.linkOtherDetails(sid, myHobbies, quals));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
