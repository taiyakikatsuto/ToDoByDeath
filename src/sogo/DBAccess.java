package sogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL =
			"jdbc:mysql://localhost:3306/todobydeath?autoReconnect=true&useSSL=false"
					+ "&serverTimezone=JST&allowPublicKeyRetrieval=true";
	private static final String DB_USER = "root";
	private static final String DB_PWD = "p1234";

	private Connection connection = null;

	public Connection getConnection() {
		return this.connection;
	}

	public void connect() {
		connect(DB_DRIVER, DB_URL, DB_USER, DB_PWD);
	}

	public void connect(String driver, String url, String user, String password) {
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(url, user, password);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			this.connection = null;
		}
	}
}
