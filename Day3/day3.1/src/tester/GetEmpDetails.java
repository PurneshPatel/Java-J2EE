package tester;

import java.util.List;
import java.util.Scanner;

import dao.EmployeeDaoImpl;
import pojos.Employee;

public class GetEmpDetails {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in))
		{
			//create singleton instance of the dao layer
			EmployeeDaoImpl empDao=new EmployeeDaoImpl();
			System.out.println("Enter dept id , strt date end date (yr-mon-day)");
			List<Employee> empList = empDao.getSelectedEmpDetails(sc.next(), sc.next(), sc.next());
			System.out.println("Emp List");
			empList.forEach(System.out::println);
			empDao.cleanUp();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
