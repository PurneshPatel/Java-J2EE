package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.fetchDBConnection;

import pojos.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {
	// state
	private Connection cn;
	private PreparedStatement pst1, pst2,pst3;

	// constr will be invokes exactly once by the top layer(main(...) based tester)
	// .
	public EmployeeDaoImpl() throws ClassNotFoundException, SQLException {
		// get cn from DBUtils
		cn = fetchDBConnection();
		// create pst1 : select
		String sql = "select empid,name,salary,join_date from my_emp where deptid=? and join_date between ? and ?";
		pst1 = cn.prepareStatement(sql);
		// create pst2 : insert
		pst2 = cn.prepareStatement("insert into my_emp values(default,?,?,?,?,?)");
		//create pst3 : update
		pst3=cn.prepareStatement("update my_emp set deptid=?,salary=salary+? where empid=?");
		System.out.println("emp dao created....");
	}

	@Override
	public List<Employee> getSelectedEmpDetails(String deptId, String beginDate, String endDate) throws SQLException {
		List<Employee> list = new ArrayList<>();
		// set IN params
		pst1.setString(1, deptId);
		pst1.setDate(2, Date.valueOf(beginDate));
		pst1.setDate(3, Date.valueOf(endDate));
		// exec query : executeQuery() : RST
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				list.add(new Employee(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getDate(4)));
		}

		return list;
	}

	@Override
	public String addEmpDetails(Employee emp) throws SQLException {
		// set IN params : name | addr | salary | deptid | join_date
		pst2.setString(1, emp.getName());// name
		pst2.setString(2, emp.getAddress());// address
		pst2.setDouble(3, emp.getSalary());// sal
		pst2.setString(4, emp.getDeptId());// dept id
		pst2.setDate(5, emp.getJoinDate());// join date
		int updateCount = pst2.executeUpdate();// DML
		if (updateCount == 1)
			return "Emp details inserted ....";
		return "Inserting emp details failed!!!!!!";

	}
	
	

	@Override
	public String updateEmpDetails(int empId, String newDept, double salIncrement) throws SQLException {
		// set IN params
		pst3.setString(1, newDept);
		pst3.setDouble(2, salIncrement);
		pst3.setInt(3, empId);
		int updateCount=pst3.executeUpdate();
		if (updateCount == 1)
			return "Emp details updated ....";
		return "Updating emp details failed!!!!!!";
		
	}

	// add a method to close DB resources
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		if (cn != null)
			cn.close();
		System.out.println("emp dao clened up !");

	}

}
