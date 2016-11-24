package models;
import play.db.DB;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchManager {
	static public String keyword = "";
	// constructor
	public SearchManager() {}

	static public void setKeyword(String word) {
		keyword = word;
	}

	static public String getKeyword() {
		return keyword;
	}
}
