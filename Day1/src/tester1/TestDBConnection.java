package tester1;

import java.sql.Connection;

import utils1.DBUtils;

public class TestDBConnection {

	public static void main(String[] args) {
		try (Connection cn = DBUtils.fetchDBConnection()) {
			System.out.println("connected to DB " + cn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
