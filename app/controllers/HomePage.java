package controllers;

import play.*;
import play.db.DB;
import play.mvc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.*;

public class HomePage extends Controller {
	static String className = "HomePage";

	@Before
	public static void checkAuthorized() {
		if (!UserManager.signed()) {
			System.out.println("User is not signed");
			LoginController.index();
		}
	}

    public static void index() {
		System.out.println(UserManager.currentUser);
		List <Item> items = downloadItems();
		render(className + "/index.html", items);
    }

	public static void addItem(String name, String type, String size, String description) throws SQLException {
		Connection conn = DB.getConnection();
		String query = "INSERT INTO `DatabaseProject`.`Items` (`ID`, `UID`, `name`, `type`, `size`, `description`, `imageUrl`) VALUES (NULL, '" + UserManager.currentUser.id + "', '" + name + "', '" + type + "', '" + size + "', '" + description + "', '" + description + "');";
		System.out.println(query);
		System.out.println(query);
		conn.createStatement().executeUpdate(query);
	}

	public static List<Item> downloadItems() {
		// Database connection
		List<Item> items = new ArrayList<>();
		try {
			Connection conn = DB.getConnection();
	    	String query = "select * FROM Items WHERE UID = " + UserManager.currentUser.id + ";";
	    	ResultSet rs = conn.createStatement().executeQuery(query);
	        // going through the results
	    	while (rs.next()) {
	    		Item item = new Item(rs.getInt("ID"), rs.getInt("UID"), rs.getString("name"),
	    			rs.getString("type"), rs.getString("size"), rs.getString("description"), "empty");
				items.add(item);
	    	}
		}	catch (Exception e) {
			System.out.println(e);
		}
		return items;
	}
}
