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

public class EventsController extends Controller {
	static String className = "EventsController";

	@Before
	public static void checkAuthorized() {
		if (!UserManager.signed()) {
			System.out.println("User is not signed");
			LoginController.index();
		}
	}

    public static void index() {
		List <Event> events = downloadAllEvents();
		List <EventUser> myEvents = downloadAllEventUsers();
		for (Event event: events) {
			for (EventUser myEvent: myEvents) {
				if (event.id == myEvent.eid) {
					event.counter++;
					if (myEvent.uid == UserManager.currentUser.id)
						event.currentUser = "You are registered";
				}
			}
		}
		render(className + "/index.html", events);
   	}


   	public static void showParticipants(String itemId) {
   		System.out.println(itemId);
   	}

   	public static void registerOnEvent(String eventId) {
   		try {
   			Connection conn = DB.getConnection();
			String query = "INSERT INTO `EventUser` (`euid`, `uid`, `eid`) VALUES (NULL, '" + UserManager.currentUser.id + "', '" + eventId + "');";
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
			renderText("successOnRegister");

		} catch (Exception e) {
			System.out.println(e);
		}
   	}

   	public static void deregisterOnEvent(String eventId) {
   		try {
   			Connection conn = DB.getConnection();
			String query = "DELETE FROM `EventUser` WHERE uid = " + UserManager.currentUser.id + " and eid = " + eventId + ";";
			System.out.println(query);
			conn.createStatement().executeUpdate(query);
			renderText("successOnDeRegister");
		} catch (Exception e) {
			System.out.println(e);
		}
   	}

   	public static List<EventUser> downloadAllEventUsers() {
   		List<EventUser> events = new ArrayList<EventUser>();
		try {
			Connection conn = DB.getConnection();
	    	String query = "select * FROM EventUser";
	    	System.out.println(query);
	    	ResultSet rs = conn.createStatement().executeQuery(query);
	    	while (rs.next()) {
	    		EventUser event = new EventUser(rs.getInt("euid"), rs.getInt("uid"), rs.getInt("eid"));
	    		events.add(event);
	    	}
		}	catch (Exception e) {
			System.out.println(e);
		}
		return events;
   	}

	public static List<Event> downloadAllEvents() {
		// Database connection
		List<Event> events = new ArrayList<Event>();
		try {
			Connection conn = DB.getConnection();
	    	String query = "select * FROM Events";
	    	ResultSet rs = conn.createStatement().executeQuery(query);
	    	while (rs.next()) {
	    		Event event = new Event(rs.getInt("ID"), rs.getString("title"), rs.getString("description"), rs.getString("date"), rs.getString("eventImage"));
	    		events.add(event);
	    	}
		}	catch (Exception e) {
			System.out.println(e);
		}
		return events;
	}
}
