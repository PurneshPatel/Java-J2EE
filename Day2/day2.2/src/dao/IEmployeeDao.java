package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Employee;

public interface IEmployeeDao {
//add a method declaration for getting emp details
	List<Employee> getSelectedEmpDetails(String deptId, String beginDate, String endDate) throws SQLException;
	//add a method declaration to insert emp details
	String addEmpDetails(Employee emp) throws SQLException;
	//add a method declaration to update emp details
	String updateEmpDetails(int empId,String newDept,double salIncrement) throws SQLException;
}
