package models;
import play.db.DB;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
	static public User currentUser = null;

	// constructor
	public UserManager() {}

	static public void create(User user) {
		try {
			Connection conn = DB.getConnection();
			String query = "INSERT INTO `DatabaseProject`.`Users` (`ID`, `firstName`, `lastName`, `password`, `email`, `profileUrl`) VALUES (NULL, '" + user.name + "', '" + user.surname + "', '" + user.password + "', '" + user.email + "', '" + user.password + "');";
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
			currentUser = user;
		} catch (Exception e) {
			System.out.println(e);
			currentUser = null;
		}
	}

	static public void login(String email, String password) throws SQLException {
		Connection conn = DB.getConnection();
		String query = "select * FROM Users WHERE " + "email = '" + email + "' and password = '" + password + "';";
		ResultSet rs = conn.createStatement().executeQuery(query);
		if (rs.next()) {
			System.out.println("found a user");
			currentUser = new User(rs.getInt("ID"), rs.getString("email"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("profileUrl"));
		} else {
			currentUser = null;
		}
	}

	static public void logout() {
		currentUser = null;
	}

	static public Boolean signed() {
		if (currentUser != null)
			return true;
		return false;
	}
}
