package controllers;

import play.*;
import play.db.DB;
import play.mvc.*;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.File;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.*;

public class MyItemsController extends Controller {
	static String className = "MyItemsController";

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

    public static void deleteItem(String itemId) {
    	System.out.println(itemId);
    	try {
			Connection conn = DB.getConnection();
			String query = "DELETE FROM `Items` WHERE `items`.`ID` = " + itemId;
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
			renderText("successOnDelete");
		}	catch (Exception e) {
			System.out.println(e);
			renderText("failOnDelete");
		}
    }

	public static List<Item> downloadItems() {
		// Database connection
		List<Item> items = new ArrayList<Item>();
		try {
			Connection conn = DB.getConnection();
	    	String query = "select * FROM Items WHERE UID = " + UserManager.currentUser.id + ";";
	    	ResultSet rs = conn.createStatement().executeQuery(query);
	        // going through the results
	    	while (rs.next()) {
	    		Item item = new Item(rs.getInt("ID"), rs.getInt("UID"), rs.getString("name"),
	    			rs.getString("type"), rs.getString("size"), rs.getString("description"), rs.getString("imageUrl"), rs.getString("date"));
				items.add(item);
	    	}
		}	catch (Exception e) {
			System.out.println(e);
		}
		return items;
	}
}
