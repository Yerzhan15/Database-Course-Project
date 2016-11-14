package models;
import play.db.DB;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
	static public List<User> users;

	// constructor
	public UserManager() {
		users = new ArrayList<>();
		try {
			loadUsers();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void create(User user) throws SQLException {
			Connection conn = DB.getConnection();
			String query = "INSERT INTO `DatabaseProject`.`Users` (`ID`, `firstName`, `lastName`, `password`, `email`, `profileUrl`) VALUES (NULL, '" + user.name + "', '" + user.surname + "', '" + user.password + "', '" + user.email + "', '" + user.password + "');";
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
	}

	public int login(String email, String password) throws SQLException {
		Connection conn = DB.getConnection();
		String query = "select * FROM Users WHERE " + "email = '" + email + "' and password = '" + password + "';";
		ResultSet rs = conn.createStatement().executeQuery(query);
		if (rs.next())
			return rs.getInt("ID");
		return -1;
	}

	public void loadUsers() throws SQLException {
		// Database connection
		Connection conn = DB.getConnection();
    	String query = "select * FROM Users";
    	ResultSet rs = conn.createStatement().executeQuery(query);
        // going through the results
    	while (rs.next()) {
    		User user = new User(rs.getInt("ID"), rs.getString("email"),
    			rs.getString("firstName"), rs.getString("lastName"), rs.getString("profileUrl"), "empty");
    		users.add(user);
    	}
	}

	public List<User> getUsers() {
		return users;
	}
}
