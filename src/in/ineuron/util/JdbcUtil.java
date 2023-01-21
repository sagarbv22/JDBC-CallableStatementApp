package in.ineuron.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	public static Connection getConnection() {

		Connection connection = null;

		try {

			FileInputStream fis = new FileInputStream("Application.properties");
			Properties properties = new Properties();
			properties.load(fis);

			if (properties != null) {
				String url = properties.getProperty("url");
				String userId = properties.getProperty("userId");
				String passWord = properties.getProperty("passWord");

				connection = DriverManager.getConnection(url, userId, passWord);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	public static void close(Connection c, Statement s, ResultSet r) {

		try {
			if (c != null)
				c.close();
			if (s != null)
				s.close();
			if (r != null)
				r.close();

		} catch (SQLException sq) {
			sq.printStackTrace();
		}

	}

}
