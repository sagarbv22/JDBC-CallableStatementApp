package in.ineuron.main;

import java.sql.*;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class StoredProcedureId {

	private static final String storedProcedureCall = "{CALL P_GET_PRODUCT_DETAILS_BY_ID(?,?,?,?)}";

	public static void main(String[] args) {
		//resource
		Connection connection = null;
		CallableStatement cstmt = null;

		try {
			// establish the connection
			connection = JdbcUtil.getConnection();
			// request for storedProcedure
			if (connection != null)
				cstmt = connection.prepareCall(storedProcedureCall);

			if (cstmt != null) {
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter the id::");
				Integer id = sc.nextInt();

				// set the values
				cstmt.setInt(1, id);
				sc.close();
			}
			// set the dataType of output values
			if (cstmt != null) {
				cstmt.registerOutParameter(2, Types.VARCHAR);
				cstmt.registerOutParameter(3, Types.INTEGER);
				cstmt.registerOutParameter(4, Types.INTEGER);
			}

			if (cstmt != null)
				//execute the storedProcedure
				cstmt.execute();

			if (cstmt != null) {
				//get the values
				System.out.println("NAME  OF PRODUCT::" + cstmt.getString(2));
				System.out.println("PRICE OF PRODUCT::" + cstmt.getInt(3));
				System.out.println("QTY   OF PRODUCT::" + cstmt.getInt(4));

			}

		} catch (SQLException sq) {
			sq.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		finally {
			//close the connection
			JdbcUtil.close(connection, cstmt, null);
		}

	}

}
