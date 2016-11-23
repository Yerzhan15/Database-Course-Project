package models;

public class Event {
	public int id;
    public String title;
    public String description;
    public String date;
    public String eventImage;
    public String currentUser;
    public int counter;

	// constructor
	public Event() {

	}
    
	public Event(int id, String title, String description, String date, String eventImage) {
		this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.eventImage = eventImage;
        this.currentUser = "Register";
        this.counter = 0;
	}
}
