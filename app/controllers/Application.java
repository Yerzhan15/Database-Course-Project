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

    public static void index() {
    	render();
    }

    public static void createUser(String userName, String userSurname, String userEmail, String userPassword) {
		User user = new User(12, userEmail, userPassword, userName, userSurname, "");
		try {
			manager.create(user);
		} catch (Exception e) {
			System.out.println(e);
		}
    }

	public static void loginUser(String userEmail, String userPassword) {
		try {
			System.out.println(userEmail + " " + userPassword);
			int loginResult = manager.login(userEmail, userPassword);
			if (loginResult != -1) {
				Authorization.loggedIn = true;
				Authorization.userID = loginResult;
				System.out.println(loginResult);
				renderText("success");
			}	else {
				Authorization.loggedIn = false;
				renderText("error");
			}
		} catch (Exception e) {
			renderText("error");
		}
	}
}
