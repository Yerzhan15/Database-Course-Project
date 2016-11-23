package controllers;

import play.*;
import play.db.DB;
import play.mvc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.*;

public class LoginController extends Controller {
	static String className = "Application";

    public static void index() {
    	render();
    }

    public static void createUser(String userName, String userSurname, String userEmail, String userPhone, String userPassword) {
		User user = new User(12, userEmail, userPhone, userPassword, userName, userSurname, "");
		UserManager.create(user);
		loginUser(userEmail, userPassword);
    }

	public static void loginUser(String userEmail, String userPassword) {
		try {
			System.out.println("Logging in" + userEmail + " " + userPassword);
			UserManager.login(userEmail, userPassword);
			if (UserManager.signed()) {
				renderText("success");
			}	else {
				renderText("error");
			}
		} catch (Exception e) {
			renderText("error");
		}
	}

	public static void logoutUser() {
		UserManager.logout();
		renderText("successOnLogout");
	}

}
