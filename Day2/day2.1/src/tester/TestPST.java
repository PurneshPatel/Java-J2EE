package tester;

import java.sql.*;
import java.util.Scanner;

import static utils.DBUtils.fetchDBConnection;

public class TestPST {

	public static void main(String[] args) {
		String sql = "select empid,name,salary,join_date from my_emp where deptid=? and join_date between ? and ?";
		try (Scanner sc = new Scanner(System.in);
				// db cn
				Connection cn = fetchDBConnection();
				// PST : public PST prepareStatement(String sql) throws SqlExc
				PreparedStatement pst = cn.prepareStatement(sql);) {
			System.out.println("Enter dept id , begin n end dates(yr-mon-day) to fetch emp details");
			// set IN params
			pst.setString(1, sc.next());
			pst.setDate(2, Date.valueOf(sc.next()));
			pst.setDate(3, Date.valueOf(sc.next()));
			try(ResultSet rst=pst.executeQuery())
			{
				while(rst.next())
					System.out.printf("ID %d Name %s Salary %.1f Join Date %s %n",rst.getInt(1)
							,rst.getString(2),rst.getDouble(3),rst.getDate(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
