package tester;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.EmployeeDaoImpl;
import pojos.Employee;

public class InsertEmpDetails {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create singleton instance of the dao layer
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			System.out.println("Enter emp details : name,  address,  salary,  deptId,  joinDate(yr-mon-day) ");
			String message = empDao.addEmpDetails(
					new Employee(sc.next(), sc.next(), sc.nextDouble(), sc.next(), Date.valueOf(sc.next())));
			System.out.println("Emp insertion status mesg "+message);
			empDao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
