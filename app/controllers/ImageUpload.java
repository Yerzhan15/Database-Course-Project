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

public class ImageUpload extends Controller {
	static String className = "ImageUpload";

	@Before
	public static void checkAuthorized() {
		if (!UserManager.signed()) {
			System.out.println("User is not signed");
			LoginController.index();
		}
	}

    public static void index() {
		render(className + "/index.html");
    }

    public static void upload(File photo) {
        try {
            String title = request.params.get("itemName");
            String description = request.params.get("itemDescription");
			String destFolder = System.getProperty("user.dir");
            String destFile = destFolder + "/public/images/uploads" + File.separator + UserManager.currentUser.id + photo.getName();
            System.out.println(destFile);

            try {
    			FileUtils.moveFile(photo, new File(destFile));
    		} catch (IOException e) {
    			Logger.info(e.toString());
    		}
			renderText("successOnUpload");
		} catch (Exception e) {
			renderText("error");
		}
    }
}
