package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.User;
import pojos.UserRole;

public class RestoreImage {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter user id ");
			int id=sc.nextInt();
			sc.nextLine();//reads off pending new line from scanner
			System.out.println("Enter image file path to create a new file");
			String path=sc.nextLine();
			// create dao instance n test API
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println(dao.restoreImage(id, path));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
