package com.plapro.beans;

import java.sql.Date;
import java.text.DateFormat;

public class Activity {
	private int id;
	private int idUser;
	private String PseudoUser;
	private String link;
	private String type;
	private String createdAt;
	private String text;
	private int idProject;

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

	public String getPseudoUser() {
		return PseudoUser;
	}

	public void setPseudoUser(String pseudoUser) {
		PseudoUser = pseudoUser;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", idUser=" + idUser + ", PseudoUser=" + PseudoUser + ", link=" + link + ", type="
				+ type + ", createdAt=" + createdAt + ", text=" + text + ", idProject=" + idProject + "]";
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
