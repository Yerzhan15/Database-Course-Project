package models;

public class User {
	public int id;
	public String email;
	public String phone;
	public String name;
	public String surname;
	public String profileUrl;
	public String password;

	// constructor
	public User() {

	}
	public User(int id, String email, String phone, String password, String name, String surname, String profileUrl) {
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.profileUrl = profileUrl;
	}

	public String toString() {
		return id + " " + email + " " + name + " " + surname;
	}
}
