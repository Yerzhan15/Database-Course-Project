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
			String query = "INSERT INTO `BucketList`.`Users` (`userID`, `userName`, `userSurname`, `password`, `userEmail`, `profileUrl`) VALUES (NULL, '" + user.name + "', '" + user.surname + "', '" + user.password + "', '" + user.email + "', '" + user.password + "');";
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
	}

	public Boolean login(String email, String password) throws SQLException {
		Connection conn = DB.getConnection();
		String query = "select * FROM Users WHERE " + "userEmail = '" + email + "' and password = '" + password + "';";
		ResultSet rs = conn.createStatement().executeQuery(query);
		if (rs.next())
			return true;
		return false;
	}

	public void loadUsers() throws SQLException {
		// Database connection
		Connection conn = DB.getConnection();
    	String query = "select * FROM Users";
    	ResultSet rs = conn.createStatement().executeQuery(query);
        // going through the results
    	while (rs.next()) {
    		User user = new User(rs.getInt("userID"), rs.getString("userEmail"),
    			rs.getString("userName"), rs.getString("userSurname"), rs.getString("profileUrl"), "empty");
    		users.add(user);
    	}
	}

	public List<User> getUsers() {
		return users;
	}
}
