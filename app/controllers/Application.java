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
	static UserManager manager = new UserManager();

    public static void index() throws SQLException {
    	render(className + "/index.html");
    }

    public static void createUser(String userName, String userSurname, String userEmail, String userPassword) {
		User user = new User(12, userEmail, userPassword, userName, userSurname, "");
		try {
			manager.create(user);
		} catch (Exception e) {
			System.out.println(e);
		}
    }

	public static void loginUser(String userEmail, String userPassword) throws SQLException {
		try {
			if (manager.login(userEmail, userPassword)) {
				System.out.println("Success!");
				System.out.println("Success2!");
				redirect("http://www.google.com");
			}	else {
				System.out.println("Fail to login!");
			}
		}	 catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Success3!");
	}

	public static void redirect(String url) {
        redirect(url, true);
    }
}
