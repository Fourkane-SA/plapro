package com.plapro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private String url;
	private String pseudo;
	private String mdp;

	DaoFactory(String url, String pseudo, String mdp) {
		this.url = url;
		this.pseudo = pseudo;
		this.mdp = mdp;
	}

	public static DaoFactory getInstance() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}

		DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3305/plapro", "root", "");
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Connection connexion = DriverManager.getConnection(url, pseudo, mdp);
		connexion.setAutoCommit(false);
		return connexion;
	}

	public AccountDao accountDao() {
		return new AccountDaoImpl(this);
	}

	public ProjectDao projectDao() {
		return new ProjectDaoImpl(this);
	}

	public FollowDao followDao() {
		return new FollowDaoImpl(this);
	}

	public NotificationsDao notifiationsDao() {
		return new NotificationsDaoImpl(this);
	}

	public SearchDao searchDao() {
		return new SearchDaoImpl(this);
	}

	public MessageDao messageDao() {
		return new MessageDaoImpl(this);
	}

	public TaskDao taskDao() {
		return new TaskDaoImpl(this);
	}

	public ActivityDao activityDao() {
		return new ActivityDaoImpl(this);
	}

}
