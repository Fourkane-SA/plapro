package com.plapro.beans;

import java.sql.Date;
import java.text.DateFormat;

public class Notifications {
	private int id;
	private int idUser;
	private String link;
	private String text;
	private String type;
	private int seen;
	private String createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
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
