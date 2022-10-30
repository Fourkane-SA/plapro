package com.plapro.beans;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;

public class Project {
	private int id;
	private String name;
	private String shortDescription;
	private String visibility;
	private String status;
	private String creationDate;
	private String updateTime;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", shortDescription=" + shortDescription + ", visibility="
				+ visibility + ", status=" + status + ", creationDate=" + creationDate + ", updateTime=" + updateTime
				+ ", description=" + description + "]";
	}
	
	public void setCreationDateToString() {
		String completeDate [] = creationDate.split(" ");
		Date date=Date.valueOf(completeDate[0]);
		String time = completeDate[1];
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.FULL,
		        DateFormat.SHORT);
		String newDate = fullDateFormat.format(date);
	    String finalString = newDate.substring(newDate.lastIndexOf(" ",newDate.length()));
	    creationDate = newDate.replace(finalString, "" + " à " + time);
	}
	
	public void setUpdateTimeToString() {
		String completeDate [] = updateTime.split(" ");
		Date date=Date.valueOf(completeDate[0]);
		String time = completeDate[1];
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.FULL,
		        DateFormat.SHORT);
		String newDate = fullDateFormat.format(date);
	    String finalString = newDate.substring(newDate.lastIndexOf(" ",newDate.length()));
	    updateTime = newDate.replace(finalString, "" + " à " + time);
	}
}
