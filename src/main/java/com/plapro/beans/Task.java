package com.plapro.beans;

import java.text.DateFormat;
import java.sql.Date;  

public class Task {
	private int id;
	private String name;
	private String start;
	private String end;
	private int progress;
	private String finish;
	private String description;
	private int idGroup;
	private int idProject;
	private int idUser;

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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + ", progress=" + progress
				+ ", finish=" + finish + ", description=" + description + ", idGroup=" + idGroup + ", idProject="
				+ idProject + ", idUser=" + idUser + "]";
	}
	
	public void setDateEndToString() {
		Date date=Date.valueOf(end);
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.FULL,
		        DateFormat.SHORT);
		String newDate = fullDateFormat.format(date);
	    String finalString = newDate.substring(newDate.lastIndexOf(" ",newDate.length()));
	    end = newDate.replace(finalString, "");
	}
	
	public void setDateStartToString() {
		Date date=Date.valueOf(start);
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.FULL,
		        DateFormat.SHORT);
		String newDate = fullDateFormat.format(date);
	    String finalString = newDate.substring(newDate.lastIndexOf(" ",newDate.length()));
	    start = newDate.replace(finalString, "");
	}
	
	public void setDateFinishToString() {
		Date date=Date.valueOf(finish);
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.FULL,
		        DateFormat.SHORT);
		String newDate = fullDateFormat.format(date);
	    String finalString = newDate.substring(newDate.lastIndexOf(" ",newDate.length()));
	    finish = newDate.replace(finalString, "");
	}
}