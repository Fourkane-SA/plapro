package com.plapro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.plapro.beans.Message;

public class MessageDaoImpl implements MessageDao {
	private DaoFactory daoFactory;

	MessageDaoImpl(DaoFactory daofactory) {
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
	public void addMessage(int idUserSender, int idConversation, String text) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO message (idUserSender, idConversation, text) VALUES ('" + idUserSender + "', '"
				+ idConversation + "', '" + text + "');";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Message> getConversation(int idConversation) {
		List<Message> conversation = new ArrayList<Message>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT m.*, u.pseudo FROM message m " + "JOIN User u ON u.id = m.idUserSender "
				+ "WHERE idConversation = " + idConversation + " ORDER BY time;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				Message m = new Message();
				m.setId(result.getInt("id"));
				m.setIdConversation(result.getInt("idConversation"));
				m.setIdUserSender(result.getInt("idUserSender"));
				m.setIsRead(result.getInt("isRead"));
				m.setText(result.getString("text"));
				m.setTime(result.getString("time"));
				m.setPseudoUserSender(result.getString("pseudo"));
				m.setOtherUserPrivateDiscussion(getOtherUserPrivateConversation(idConversation, 1));
				conversation.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conversation;
	}

	@Override
	public List<Message> groupMessages(List<Message> conversation) {
		List<Message> newConversation = conversation;
		for (int i = 1; i < newConversation.size(); i++) {
			if (conversation.get(i).getIdUserSender() == conversation.get(i - 1).getIdUserSender()) {
				Message m = conversation.get(i);
				m.setIdUserSender(-1);
				newConversation.set(i, m);
			}
		}
		return newConversation;
	}

	@Override
	public int isPrivateConversationExist(int idUser1, int idUser2) {
		int n = -1;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT c.id FROM conversation c " + "JOIN memberConversation m1 ON m1.idConversation = c.id "
				+ "JOIN memberConversation m2 ON m2.idConversation = c.id " + "WHERE m1.idUser = " + idUser1
				+ " AND m2.idUser = " + idUser2 + ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				n = result.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public void createNewConversation(int idUser1, int idUser2) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO conversation VALUES();";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int idConversation = getLastConversation();
		req = "INSERT INTO memberConversation (idConversation, idUser) " + "VALUES ( " + idConversation + "," + idUser1
				+ ");";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req = "INSERT INTO memberConversation (idConversation, idUser) " + "VALUES ( " + idConversation + "," + idUser2
				+ ");";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getLastConversation() {
		int n = -1;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT MAX(id) as id FROM conversation;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				n = result.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public List<Message> getLastConversation(int idUser) {
		List<Message> conversation = new ArrayList<Message>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT m.*, u.pseudo FROM message m JOIN User u " + "on u.id = m.idUserSender INNER JOIN "
				+ "( SELECT idConversation, MAX(time) " + "AS maxTime FROM message GROUP BY idConversation )"
				+ " conv ON m.idConversation = conv.idConversation "
				+ "AND m.time = conv.maxTime AND m.idConversation IN " + "(SELECT c.id FROM conversation c "
				+ "JOIN memberConversation mc " + "ON c.id = mc.idConversation " + "WHERE mc.idUser = " + idUser + ");";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				Message m = new Message();
				m.setId(result.getInt("id"));
				m.setIdConversation(result.getInt("idConversation"));
				m.setIdUserSender(result.getInt("idUserSender"));
				m.setIsRead(result.getInt("isRead"));
				m.setText(result.getString("text"));
				m.setTime(result.getString("time"));
				m.setPseudoUserSender(result.getString("pseudo"));
				m.setOtherUserPrivateDiscussion(getOtherUserPrivateConversation(m.getIdConversation(), idUser));
				conversation.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conversation;
	}

	@Override
	public String getOtherUserPrivateConversation(int idConversation, int idUserConnected) {
		String pseudo = "";
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT u.pseudo FROM conversation c " + "JOIN memberConversation mc on mc.idConversation = c.id "
				+ "JOIN User u ON u.id = mc.idUser " + "WHERE c.id = " + idConversation + " AND u.id != "
				+ idUserConnected + ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				pseudo = result.getString("pseudo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pseudo;
	}
}
