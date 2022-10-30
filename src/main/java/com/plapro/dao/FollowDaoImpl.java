package com.plapro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FollowDaoImpl implements FollowDao {
	private DaoFactory daoFactory;

	FollowDaoImpl(DaoFactory daofactory) {
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
	public int nbFollowers(String id) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM follow WHERE id_following='" + id + "';";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				n = result.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return n;
	}

	@Override
	public int nbFollowing(String id) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM follow WHERE id_follower='" + id + "';";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				n = result.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return n;
	}

	@Override
	public List<Integer> listIdFollowers(String id) {
		List<Integer> list = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT DISTINCT id_follower FROM follow WHERE id_following=" + id + ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				list.add(result.getInt("id_follower"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Integer> listIdFollows(String id) {
		List<Integer> list = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT DISTINCT id_following FROM follow WHERE id_follower=" + id + ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				list.add(result.getInt("id_following"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void follow(String idUser, String idToFollow) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO follow (id_following, id_follower) VALUES ('" + idToFollow + "','" + idUser + "');";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unfollow(String idUser, String idToUnfollow) {
		Connection connection = null;
		Statement statement = null;
		String req = "DELETE FROM follow WHERE id_following = " + idToUnfollow + " AND id_follower = " + idUser + ";";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isFollowing(String idUser, String idToMatch) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM follow WHERE id_following=" + idToMatch + " AND id_follower=" + idUser
				+ ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				n = result.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

}
