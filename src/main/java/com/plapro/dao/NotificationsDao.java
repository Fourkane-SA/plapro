package com.plapro.dao;

import java.util.List;

import com.plapro.beans.Notifications;

public interface NotificationsDao {
	void addNotification(String idUser, String link, String text,String type);
	List<Notifications> getListNotifications(String idUser);
	void markNotificationsAsSeen(String idUser);
	boolean isAllNotificationsSeen(int idUser);
}
