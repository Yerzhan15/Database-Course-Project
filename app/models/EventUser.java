package models;

public class EventUser {
	public int euid;
    public int uid;
    public int eid;

	// constructor
	public EventUser() {

	}
    
	public EventUser(int euid, int uid, int eid) {
		this.euid = euid;
        this.uid = uid;
        this.eid = eid;
	}
}
