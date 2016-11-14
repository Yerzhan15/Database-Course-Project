package models;

public class Item {
	public int id;
    public int uid;
	public String name;
    public String type;
    public String size;
    public String description;
    public String imageUrl;

	// constructor
	public Item() {

	}
	public Item(int id, int uid, String name, String type, String size, String description, String imageUrl) {
		this.id = id;
        this.uid = id;
		this.name = name;
        this.type = type;
        this.size = size;
        this.description = description;
        this.imageUrl = imageUrl;
	}
}
