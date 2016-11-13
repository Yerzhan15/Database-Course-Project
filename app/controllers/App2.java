package controllers;

import play.*;
import play.db.DB;
import play.mvc.*;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.*;

public class App2 extends Controller {
	static String className = "App2";

    public static void index() throws SQLException {
    	render(className + "/home.html");
    }
}
