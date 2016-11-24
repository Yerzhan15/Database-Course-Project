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

public class HomeController extends Controller {
	static String className = "HomeController";
	static String keyword = "";

	@Before
	public static void checkAuthorized() {
		if (!UserManager.signed()) {
			System.out.println("User is not signed");
			LoginController.index();
		}
	}

    public static void index() {
		System.out.println(UserManager.currentUser);
		List <Item> items = downloadAllItems();
		render(className + "/index.html", items);
   	}

   	public static void index2() {
		List <Item> items = searchForItems(SearchManager.getKeyword());
		String keyword = SearchManager.getKeyword();
		render(className + "/index.html", items, keyword);
   	}

	public static List<Item> downloadAllItems() {
		// Database connection
		List<Item> items = new ArrayList<Item>();
		try {
			Connection conn = DB.getConnection();
	    	String query = "select * FROM Items";
	    	ResultSet rs = conn.createStatement().executeQuery(query);
	        // going through the results
	    	while (rs.next()) {
	    		Item item = new Item(rs.getInt("ID"), rs.getInt("UID"), rs.getString("name"),
	    			rs.getString("type"), rs.getString("size"), rs.getString("description"), rs.getString("imageUrl"), rs.getString("date"));
	    		try {
	    			String query2 = "select phone FROM Users WHERE ID = '" + item.uid + "'";
	    			ResultSet rs2 = conn.createStatement().executeQuery(query2);
	    			rs2.next();
	    			item.ownerPhone = rs2.getString("phone");
	    		} catch (Exception e){
	    			System.out.println(e);
	    		}
	    		if (item.uid != UserManager.currentUser.id) 
					items.add(item);
	    	}
		}	catch (Exception e) {
			System.out.println(e);
		}
		return items;
	}

	public static void search(String keyword) {
		SearchManager.setKeyword(keyword);
		System.out.println(keyword);
		renderText("successOnSearch");
	}

	public static List<Item> searchForItems(String keyword) {
		// Database connection
		List<Item> items = new ArrayList<Item>();
		try {
			Connection conn = DB.getConnection();
	    	String query = "select * FROM Items WHERE name LIKE '%" + keyword + "%' or description LIKE '%" + keyword + "%';";
	    	ResultSet rs = conn.createStatement().executeQuery(query);
	    	System.out.println(query);
	        // going through the results
	    	while (rs.next()) {
	    		Item item = new Item(rs.getInt("ID"), rs.getInt("UID"), rs.getString("name"),
	    			rs.getString("type"), rs.getString("size"), rs.getString("description"), rs.getString("imageUrl"), rs.getString("date"));
	    		try {
	    			String query2 = "select phone FROM Users WHERE ID = '" + item.uid + "'";
	    			ResultSet rs2 = conn.createStatement().executeQuery(query2);
	    			rs2.next();
	    			item.ownerPhone = rs2.getString("phone");
	    		} catch (Exception e){
	    			System.out.println(e);
	    		}
	    		if (item.uid != UserManager.currentUser.id) 
					items.add(item);
	    	}
		}	catch (Exception e) {
			System.out.println(e);
		}
		return items;
	}
}
