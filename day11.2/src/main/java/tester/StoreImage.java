package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.User;
import pojos.UserRole;

public class StoreImage {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter email ");
			String email=sc.nextLine();
			System.out.println("Enter image file path");
			String path=sc.nextLine();
			// create dao instance n test API
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println(dao.storeImage(email, path));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
