package com.plapro.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.plapro.beans.Group;
import com.plapro.beans.Project;
import com.plapro.beans.User;

public class ProjectDaoImpl implements ProjectDao {
	private DaoFactory daoFactory;

	ProjectDaoImpl(DaoFactory daofactory) {
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
	public void addProject(Project p) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO Project (name, shortDescription, visibility, status) values ('" + p.getName() + "', '"
				+ p.getShortDescription() + "', '" + p.getVisibility() + "', '" + p.getStatus() + "');";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Project getProject(String id) {
		Project p = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT * FROM Project WHERE id =" + id + ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				p = new Project();
				p.setCreationDate(result.getString("creationDate"));
				p.setId(result.getInt("id"));
				p.setName(result.getString("name"));
				p.setShortDescription(result.getString("shortDescription"));
				p.setStatus(result.getString("status"));
				p.setUpdateTime(result.getString("updateTime"));
				p.setVisibility(result.getString("visibility"));
				p.setDescription(result.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return p;
	}

	@Override
	public String getLastIdProject() {
		String id = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT MAX(id) AS id FROM Project ;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				id = result.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return id;
	}

	@Override
	public void addUser(String idProject, String idUser, String idGroup) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO memberProject (idProject, idUser, idGroup) VALUES ('" + idProject + "', '" + idUser + "', '" + idGroup + "');";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Project> listProjectUser(String idUser) {
		List<Project> listProject = new ArrayList<Project>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT p.* FROM Project p " + "JOIN memberProject m ON p.id = m.idProject "
				+ "JOIN User u ON m.idUser = u.id " + "WHERE u.id='" + idUser + "';";
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
	public List<User> getListUser(int idProject) {
		List<User> Users = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT u.* FROM User u "
				+ "JOIN memberProject mp ON u.id = mp.idUser "
				+ "JOIN Project p ON p.id = mp.idProject "
				+ "WHERE p.id = " + idProject + " ;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				User u = new User();
				u.setBirth(result.getString("birth"));
				u.setCreatedAt(result.getString("created_at"));
				u.setDescription(result.getString("description"));
				u.setEmail(result.getString("email"));
				u.setFirstName(result.getString("firstname"));
				u.setGender(result.getString("gender"));
				u.setId(result.getInt("id"));
				u.setLastName(result.getString("lastname"));
				u.setPassword(result.getString("password"));
				u.setPseudo(result.getString("pseudo"));
				Users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return Users;
	}

	@Override
	public void editProject(Project p) {
		Connection connection = null;
		Statement statement = null;
		String req = "UPDATE Project SET name = '"
				+ p.getName() + "', shortDescription = '"
				+ p.getShortDescription() + "', visibility = '"
				+ p.getVisibility() + "', status = '"
				+ p.getStatus() + "', updateTime = CURRENT_TIMESTAMP , description = '"
				+ p.getDescription() + "' WHERE id = " + p.getId() + ";";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int countProjectMembers(int idProject) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n "
				+ "FROM memberProject "
				+ "WHERE idProject = " + idProject + ";";
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
		return n;
	}

	@Override
	public User lastActiveMember(int idProject) {
		User u = new User();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT u.* FROM User u "
				+ "JOIN activityProject ap "
				+ "ON u.id = ap.idUser "
				+ "WHERE ap.idProject = " + idProject
				+ " AND ap.created_at = "
					+ "(SELECT MAX(created_at) FROM activityProject);";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				u.setBirth(result.getString("birth"));
				u.setCreatedAt(result.getString("created_at"));
				u.setDescription(result.getString("description"));
				u.setEmail(result.getString("email"));
				u.setFirstName(result.getString("firstname"));
				u.setGender(result.getString("gender"));
				u.setId(result.getInt("id"));
				u.setLastName(result.getString("lastname"));
				u.setPassword(result.getString("password"));
				u.setPseudo(result.getString("pseudo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public int idMostActiveMember(int idProject) {
		int id = 0;
		List<Integer> userId = new ArrayList<Integer>();
		List<Integer> userNumberActivity = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT idUser, COUNT(*) AS n "
				+ "FROM activityProject "
				+ "WHERE idProject = "
				+ + idProject + " ORDER BY idUser;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				userId.add(result.getInt("idUser"));
				userNumberActivity.add(result.getInt("n"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		id = userId.get(0);
		int pos = 0;
		for(int i=0; i<userId.size(); i++) {
			if(userNumberActivity.get(i) > userNumberActivity.get(pos)) {
				id = userId.get(i);
				pos = i;
			}
		}
		return id;
	}

	@Override
	public List<User> getOtherUser(int idProject) {
		List<User> Users = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT * FROM User EXCEPT "
				+ "(SELECT u.* FROM memberProject mp "
				+ "JOIN User u ON mp.idUser = u.id "
				+ "WHERE idProject = "
				+ idProject + ");";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				User u = new User();
				u.setBirth(result.getString("birth"));
				u.setCreatedAt(result.getString("created_at"));
				u.setDescription(result.getString("description"));
				u.setEmail(result.getString("email"));
				u.setFirstName(result.getString("firstname"));
				u.setGender(result.getString("gender"));
				u.setId(result.getInt("id"));
				u.setLastName(result.getString("lastname"));
				u.setPassword(result.getString("password"));
				u.setPseudo(result.getString("pseudo"));
				Users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return Users;
	}

	@Override
	public List<Group> getAllGroups() {
		List<Group> groups = new ArrayList<Group>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT * FROM groupProject;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				Group g = new Group();
				g.setDescription(result.getString("description"));
				g.setId(result.getInt("id"));
				g.setName(result.getString("name"));
				groups.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return groups;
	}

	@Override
	public List<User> getProjectUsersExceptConnected(int idProject, int idUser) {
		List<User> Users = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT u.* "
				+ "FROM User u JOIN memberProject mp "
				+ "ON u.id = mp.idUser "
				+ "WHERE idProject = "
				+ idProject + " AND u.id != "
				+ idUser + ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				User u = new User();
				u.setBirth(result.getString("birth"));
				u.setCreatedAt(result.getString("created_at"));
				u.setDescription(result.getString("description"));
				u.setEmail(result.getString("email"));
				u.setFirstName(result.getString("firstname"));
				u.setGender(result.getString("gender"));
				u.setId(result.getInt("id"));
				u.setLastName(result.getString("lastname"));
				u.setPassword(result.getString("password"));
				u.setPseudo(result.getString("pseudo"));
				Users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return Users;
	}

	@Override
	public void removeUser(int idProject, int idUser) {
		Connection connection = null;
		Statement statement = null;
		String req = "DELETE FROM memberProject WHERE idProject = "
				+ idProject + " AND idUser = "
				+ idUser + ";";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
