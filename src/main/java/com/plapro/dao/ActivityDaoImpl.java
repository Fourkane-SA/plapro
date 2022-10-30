package com.plapro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.plapro.beans.Activity;
import com.plapro.beans.Chart;
import com.plapro.beans.Notifications;

public class ActivityDaoImpl implements ActivityDao {
	private DaoFactory daoFactory;

	ActivityDaoImpl(DaoFactory daofactory) {
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
	public void addActivity(Activity a) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO activityProject (idUser, link, type, text, idProject) VALUES ('" + a.getIdUser()
				+ "', '" + a.getLink() + "', '" + a.getType() + "', '" + a.getText() + "', '" + a.getIdProject()
				+ "');";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req = "UPDATE Project SET updateTime = CURRENT_TIMESTAMP WHERE id = " + a.getIdProject();
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Activity> showActivityProject(int idProject) {
		List<Activity> list = new ArrayList<Activity>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT a.*, u.pseudo FROM activityProject a " + "JOIN User u on u.id = a.idUser "
				+ "WHERE idProject = " + idProject + " ORDER BY a.created_at DESC;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				Activity a = new Activity();
				a.setCreatedAt(result.getString("created_at"));
				a.setId(result.getInt("id"));
				a.setIdProject(result.getInt("idProject"));
				a.setIdUser(result.getInt("idUser"));
				a.setLink(result.getString("link"));
				a.setPseudoUser(result.getString("pseudo"));
				a.setText(result.getString("text"));
				a.setType(result.getString("type"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Chart getChartActivity(int idProject) {
		Chart c = new Chart();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT DATE(created_at) AS x, "
				+ "COUNT(*) AS y FROM activityProject "
				+ "WHERE idProject = "
				+ idProject + " GROUP BY DATE(created_at);";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				String x = result.getString("x");
				int y = result.getInt("y");
				c.addNewValue(x, y);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public String getDataPoints(Chart c) {
		Gson gsonObj = new Gson();
		Map<Object, Object> map = null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		for(int i=0; i<c.getN(); i++) {
			map = new HashMap<Object, Object>();
			map.put("label", c.getX().get(i));
			map.put("y", c.getY().get(i));
			list.add(map);
		}
		return gsonObj.toJson(list);
	}
}
