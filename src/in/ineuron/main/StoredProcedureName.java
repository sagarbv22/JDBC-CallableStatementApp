package in.ineuron.main;

import java.sql.*;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class StoredProcedureName {

	private static final String callStoredProcedure = "{call P_GET_PRODUCT_DETAILS_BY_NAME(?,?)}";

	public static void main(String[] args) {
		// resource
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet resultSet = null;
		boolean flag = false;

		try {
			// get the connection
			connection = JdbcUtil.getConnection();

			if (connection != null)
				// request for storedProcedure
				cstmt = connection.prepareCall(callStoredProcedure);

			if (cstmt != null) {

				Scanner sc = new Scanner(System.in);
				System.out.print("Enter the Name::");
				String name1 = sc.next();
				System.out.print("Enter the Name::");
				String name2 = sc.next();
				// set the values
				cstmt.setString(1, name1);
				cstmt.setString(2, name2);
				sc.close();
			}

			if (cstmt != null) {
				// execute the query
				resultSet = cstmt.executeQuery();
			}
			// process the resultSet
			if (resultSet != null) {
				System.out.println("Details below:-");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
							+ "\t\t" + resultSet.getInt(4));
					flag = true;
				}

				if (flag == false)
					System.out.println("Record not available..");
			}

		} catch (SQLException sq) {
			sq.printStackTrace();
		}

		finally {
			// close the connection
			JdbcUtil.close(connection, cstmt, resultSet);
		}
	}

}
