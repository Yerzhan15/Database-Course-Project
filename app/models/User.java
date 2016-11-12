package models;

public class User {
	public int id;
	public String email;
	public String name;
	public String surname;
	public String profileUrl;

	// constructor
	public User() {
		
	}
	public User(int id, String email, String name, String surname, String profileUrl) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.profileUrl = profileUrl;
	}
}
