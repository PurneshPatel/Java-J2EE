package tester;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.EmployeeDaoImpl;
import pojos.Employee;

public class DeleteEmpDetails {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create singleton instance of the dao layer
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			System.out.println("Enter empid to delete emp record ");
			String message = empDao.deleteEmpDetails(sc.nextInt());
			System.out.println("Emp deletion status mesg :  "+message);
			empDao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
