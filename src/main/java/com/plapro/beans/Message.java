package com.plapro.beans;

import java.sql.Date;
import java.text.DateFormat;

public class Message {
	private int id;
	private int idUserSender;
	private int idConversation;
	private String text;
	private String time;
	private int isRead;
	private String PseudoUserSender;
	private String OtherUserPrivateDiscussion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUserSender() {
		return idUserSender;
	}

	public void setIdUserSender(int idUserSender) {
		this.idUserSender = idUserSender;
	}

	public int getIdConversation() {
		return idConversation;
	}

	public void setIdConversation(int idConversation) {
		this.idConversation = idConversation;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public String getPseudoUserSender() {
		return PseudoUserSender;
	}

	public void setPseudoUserSender(String pseudoUserSender) {
		PseudoUserSender = pseudoUserSender;
	}

	public String getOtherUserPrivateDiscussion() {
		return OtherUserPrivateDiscussion;
	}

	public void setOtherUserPrivateDiscussion(String otherUserPrivateDiscussion) {
		OtherUserPrivateDiscussion = otherUserPrivateDiscussion;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", idUserSender=" + idUserSender + ", idConversation=" + idConversation + ", text="
				+ text + ", time=" + time + ", isRead=" + isRead + ", PseudoUserSender=" + PseudoUserSender
				+ ", OtherUserPrivateDiscussion=" + OtherUserPrivateDiscussion + "]";
	}

	public void setCreatedAtToString() {
		String completeDate [] = time.split(" ");
		Date date=Date.valueOf(completeDate[0]);
		String Newtime = completeDate[1];
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.FULL,
		        DateFormat.SHORT);
		String newDate = fullDateFormat.format(date);
	    String finalString = newDate.substring(newDate.lastIndexOf(" ",newDate.length()));
	    time = newDate.replace(finalString, "" + " à " + Newtime);
	}
}
