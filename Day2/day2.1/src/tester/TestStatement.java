package tester;

//display all emp details 
import static utils.DBUtils.fetchDBConnection;
import java.sql.*;

public class TestStatement {

	public static void main(String[] args) {
		try (// cn
				Connection cn = fetchDBConnection();
				// create empty statement from Connection
				// Connection i/f method
				// public Statement createStatement() throws SqlException
				Statement st = cn.createStatement();
				// execute query
				// API of Statement : public ResultSet executeQuery(String sql)throws
				// SqlException
				ResultSet rst = st.executeQuery("select * from my_emp");) {
			while (rst.next())
				System.out.printf("Emp ID %d Name %s Address %s Sal %.1f Dept Id %s Join Date %s%n",
						rst.getInt(1),
						rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5), rst.getDate(6));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
