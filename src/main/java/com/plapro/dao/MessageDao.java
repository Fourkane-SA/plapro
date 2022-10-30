package com.plapro.dao;

import java.util.List;

import com.plapro.beans.Message;

public interface MessageDao {
	void addMessage(int idUserSender, int idConversation, String text);
	List<Message> getConversation(int idConversation);
	List<Message> groupMessages(List<Message> conversation);
	int isPrivateConversationExist(int idUser1, int idUser2);
	void createNewConversation(int idUser1, int idUser2);
	int getLastConversation();
	List<Message> getLastConversation(int idUser);
	String getOtherUserPrivateConversation(int idConversation, int idUserConnected);
}
