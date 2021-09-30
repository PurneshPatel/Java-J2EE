package tester;
import static utils.DBUtils.fetchDBConnection;

import java.sql.Connection;

public class TestDBConnection {

	public static void main(String[] args) {
		try(Connection cn=fetchDBConnection())
		{
			System.out.println("success !!!! "+cn);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
