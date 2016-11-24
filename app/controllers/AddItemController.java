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

public class AddItemController extends Controller {
	static String className = "AddItemController";

	@Before
	public static void checkAuthorized() {
		if (!UserManager.signed()) {
			System.out.println("User is not signed");
			LoginController.index();
		}
	}

    public static void index() {
		System.out.println(UserManager.currentUser);
		render(className + "/index.html");
    }

	public static void upload(File photo) {
		String name = request.params.get("name");
        String type = request.params.get("type");
        String size = request.params.get("size");
		String description = request.params.get("description");
		
		try {
			String destFolder = System.getProperty("user.dir");
            String suffix = "/public/images/uploads" + File.separator + UserManager.currentUser.id + photo.getName();
            String destFile = destFolder + suffix;
            System.out.println(destFile);

            try {
    			// on successful file saving
    			Connection conn = DB.getConnection();
    			Date now = new java.util.Date();
    			String timeStamp = now.toString();
				String query = "INSERT INTO `DatabaseProject`.`Items` (`ID`, `UID`, `name`, `type`, `size`, `description`, `imageUrl`, `date`) VALUES (NULL, '" + UserManager.currentUser.id + "', '" + name + "', '" + type + "', '" + size + "', '" + description + "', '" + suffix + "', '" + timeStamp + "');";
				System.out.println(query);
				conn.createStatement().executeUpdate(query);
				FileUtils.moveFile(photo, new File(destFile));
    		} catch (IOException e) {
    			Logger.info(e.toString());
    		}
    		MyItemsController.index();
		} catch (Exception e) {
			System.out.println(e);
			renderText(e);
		}
    }

    public static void deleteItem(String itemId) {
    	System.out.println(itemId);
    	try {
			Connection conn = DB.getConnection();
			String query = "DELETE FROM `DatabaseProject`.`Items` WHERE `items`.`ID` = " + itemId;
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
			renderText("successOnDelete");
		}	catch (Exception e) {
			System.out.println(e);
			renderText("failOnDelete");
		}
    }
}
