package com.plapro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.plapro.beans.Notifications;

public class NotificationsDaoImpl implements NotificationsDao {
	private DaoFactory daoFactory;

	NotificationsDaoImpl(DaoFactory daofactory) {
		this.daoFactory = daofactory;
	}

	private Connection loadDatabase() {
		Connection connexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3305/plapro", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connexion;
	}

	@Override
	public void addNotification(String idUser, String link, String text, String type) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO Notifications (idUser, link, text, type) VALUES ('" + idUser + "', '" + link + "', '"
				+ text + "', '" + type + "');";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Notifications> getListNotifications(String idUser) {
		List<Notifications> list = new ArrayList<Notifications>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT * FROM Notifications WHERE idUser = " + idUser + "" + " ORDER BY created_at DESC;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				Notifications n = new Notifications();
				n.setCreatedAt(result.getString("created_at"));
				n.setId(result.getInt("id"));
				n.setIdUser(result.getInt("idUser"));
				n.setLink(result.getString("link"));
				n.setSeen(result.getInt("seen"));
				n.setText(result.getString("text"));
				n.setType(result.getString("type"));
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void markNotificationsAsSeen(String idUser) {
		Connection connection = null;
		Statement statement = null;
		String req = "UPDATE Notifications SET seen=1 " + "WHERE idUser=" + idUser + ";";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isAllNotificationsSeen(int idUser) {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM Notifications WHERE idUser = " + idUser + " AND seen = 0;";
		int n = 0;// number of account find in the database
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				n = result.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return n == 0;
	}

}
