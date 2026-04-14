package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
	
	private String name;
	private String email;
	private String country;
	private char gender;
	private String description;
	private String[] languages;
	private String password;
	
	public User() {
		
	}
	
	public User(String name, String email, String country, char gender, String description, List[] languages, String password) {
		this.name = name;
		this.email = email;
		this.country = country;
		this.gender = gender;
		this.description = description;
		this.languages = languages;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Name: " + name + 
				"\nEmail: " + email + 
				"\nCountry: " + country + 
				"\nGender: " + gender + 
				"\nDescription: " + description + 
				"\nLanguages:\n" + String.join("\n", languages) +
				"\nPassword:" + password;
	}
	
	public String toCsv() {
		return name + "," +
				email + "," +
				country + "," +
				gender + "," +
				description + "," +
				String.join("|", languages) +
				password;
	}
	
	public static User fromCsv(String userData) {
		String data[] = userData.split(",");
		String name = data[0];
		String email = data[1];
		String country = data[2];
		char gender = data[3].charAt(0);
		String descritpion = data[4];
		
		List<String> languages = new ArrayList<String>();
		if(data.length > 5) {
			languages = Arrays.asList(data[5].split("\\|"));
		}
		
		String password = data[6];
		
		return new User(name, email, country, gender, descritpion, languages, password);
	}
	
}