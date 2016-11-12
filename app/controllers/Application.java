package controllers;

import play.*;
import play.db.DB;
import play.mvc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.*;

public class Application extends Controller {
	static String className = "Application";

    // Application.index
    public static void index() throws SQLException {
    	List<User> users = new ArrayList<>();

        // Database connection
    	Connection conn = DB.getConnection();
        // query
    	String query = "select * FROM Users";
        // getting results
    	ResultSet rs = conn.createStatement().executeQuery(query);
        // going through the results
    	while (rs.next()) {
    		User user = new User();
    		user.name = rs.getString("userName");
            user.surname = rs.getString("userSurname");
            user.id = rs.getInt("userID");
            user.email = rs.getString("userEmail");
            user.profileUrl = rs.getString("profileUrl");
    		users.add(user);
            System.out.println("yahoo!");
    	}
    	render(className + "/index.html", users);
    }

    public static void show(Long id) throws SQLException {
        List<User> users = new ArrayList<>();

        // Database connection
        Connection conn = DB.getConnection();
        // query
        String query = "select * FROM tbl_user";
        // getting results
        ResultSet rs = conn.createStatement().executeQuery(query);
        // going through the results
        while (rs.next()) {
            User user = new User();
            user.name = rs.getString("user_name");
            user.email = "nope";
            user.surname = "nope";
            user.profileUrl = "https://yt3.ggpht.com/-v0soe-ievYE/AAAAAAAAAAI/AAAAAAAAAAA/OixOH_h84Po/s900-c-k-no-mo-rj-c0xffffff/photo.jpg";
            users.add(user);
        }
        System.out.println(id);
        render(className + "/index.html", users);
    }
}