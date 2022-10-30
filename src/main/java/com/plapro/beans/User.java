package com.plapro.beans;

import java.sql.Date;
import java.text.DateFormat;

public class User {
	private int id;
	private String pseudo;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String birth;
	private String createdAt;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pseudo=" + pseudo + ", password=" + password + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", gender=" + gender + ", birth=" + birth + ", createdAt="
				+ createdAt + ", description=" + description + "]";
	}
	
	public void setCreatedAtToString() {
		String completeDate [] = createdAt.split(" ");
		Date date=Date.valueOf(completeDate[0]);
		String time = completeDate[1];
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.FULL,
		        DateFormat.SHORT);
		String newDate = fullDateFormat.format(date);
	    String finalString = newDate.substring(newDate.lastIndexOf(" ",newDate.length()));
	    createdAt = newDate.replace(finalString, "" + " à " + time);
	}
}
