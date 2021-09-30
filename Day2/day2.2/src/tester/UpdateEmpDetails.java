package tester;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.EmployeeDaoImpl;
import pojos.Employee;

public class UpdateEmpDetails {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create singleton instance of the dao layer
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			System.out.println("Enter empid new dept n sal incrememt ");
			String message = empDao.updateEmpDetails(sc.nextInt(), sc.next(), sc.nextDouble());
			System.out.println("Emp updation status mesg "+message);
			empDao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
