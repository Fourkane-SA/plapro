package com.plapro.dao;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.plapro.beans.Project;
import com.plapro.beans.User;

public class SearchDaoImpl implements SearchDao {
	private DaoFactory daoFactory;

	SearchDaoImpl(DaoFactory daofactory) {
		this.daoFactory = daofactory;
	}

	@Override
	public List<Project> searchProject(String search) {
		List<Project> listProject = new ArrayList<Project>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT DISTINCT * FROM Project WHERE name LIKE '%" + search + "%' OR shortDescription LIKE '%"
				+ search + "%';";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				Project p = new Project();
				p.setCreationDate(result.getString("creationDate"));
				p.setId(result.getInt("id"));
				p.setName(result.getString("name"));
				p.setShortDescription(result.getString("shortDescription"));
				p.setStatus(result.getString("status"));
				p.setUpdateTime(result.getString("updateTime"));
				p.setVisibility(result.getString("visibility"));
				listProject.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listProject;
	}

	@Override
	public List<User> searchUser(String search) {
		List<User> listUser = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT DISTINCT * FROM User WHERE pseudo LIKE '%" + search + "%' OR firstname LIKE '%" + search
				+ "%' OR lastname LIKE '%" + search + "%';";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				User u = new User();
				u.setBirth(result.getString("birth"));
				u.setEmail(result.getString("email"));
				u.setFirstName(result.getString("firstname"));
				u.setGender(result.getString("gender"));
				u.setId(result.getInt("id"));
				u.setLastName(result.getString("lastname"));
				u.setPassword(result.getString("password"));
				u.setPseudo(result.getString("pseudo"));
				u.setCreatedAt(result.getString("created_at"));
				u.setDescription(result.getString("description"));
				listUser.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUser;
	}

}
