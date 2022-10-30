package com.plapro.dao;

import com.plapro.beans.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
	private DaoFactory daoFactory;

	AccountDaoImpl(DaoFactory daofactory) {
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
	public boolean verifyRegister(User u) {
		if (isAvailable(u)) {
			addUser(u);
			return true;
		}
		return false;
	}

	@Override
	public boolean verifyLogin(User u) {
		if (isExist(u.getPseudo())) {
			User user = getUser(getId(u.getPseudo()));
			return u.getPassword().equals(user.getPassword());
		}
		return false;
	}

	@Override
	public User getUser(String id) {
		User u = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT * FROM User WHERE id =" + id + ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				u = new User();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return u;
	}

	@Override
	public boolean isAvailable(User u) {
		return isAvailablePseudo(u.getPseudo()) && isAvailableEmail(u.getEmail());
	}

	@Override
	public void addUser(User u) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO User(pseudo, password, email, firstname, lastname, gender, birth) VALUES ('"
				+ u.getPseudo() + "', '" + u.getPassword() + "', '" + u.getEmail() + "', '" + u.getFirstName() + "', '"
				+ u.getLastName() + "', '" + u.getGender() + "', '" + u.getBirth() + "');";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isExist(String pseudo) {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM User WHERE pseudo = '" + pseudo + "';";
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
		return n == 1; // The pseudo is in the database
	}

	@Override
	public String getId(String pseudo) {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT id FROM User WHERE pseudo = '" + pseudo + "';";
		String id = null;
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
	public int nbProject(String id) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM memberProject WHERE idUser='" + id + "';";
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
	public String updateUser(User user, User newUser, String password) {
		if (!user.getPassword().equals(password)) {
			System.out.println("Mot de passe incorrect");
			return "password";
		}
		if (!user.getPseudo().equals(newUser.getPseudo())) {
			if (!isAvailablePseudo(newUser.getPseudo())) {
				System.out.println("Le pseudo est déjà pris");
				return "pseudo";
			}
		}
		if (!user.getEmail().equals(newUser.getEmail())) {
			if (!isAvailableEmail(newUser.getEmail())) {
				System.out.println("L'adresse mail a déjà été utilisée");
				return "email";
			}
		}
		if (newUser.getPassword() == "")
			newUser.setPassword(password);
		Connection connection = null;
		Statement statement = null;
		String req = "UPDATE User SET " + "pseudo='" + newUser.getPseudo() + "', " + "password='"
				+ newUser.getPassword() + "', " + "email='" + newUser.getEmail() + "', " + "firstname='"
				+ newUser.getFirstName() + "', " + "lastname='" + newUser.getLastName() + "', " + "gender='"
				+ newUser.getGender() + "', " + "birth='" + newUser.getBirth() + "', " + "description='"
				+ newUser.getDescription() + "' " + "WHERE id='" + user.getId() + "';";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "no";
	}

	@Override
	public boolean isAvailableEmail(String email) {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT DISTINCT COUNT(*) AS n FROM User WHERE email='" + email + "';";
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

	@Override
	public boolean isAvailablePseudo(String pseudo) {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT DISTINCT COUNT(*) AS n FROM User WHERE pseudo='" + pseudo + "';";
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

	@Override
	public List<User> listAllUserWithoutLogin(int id) {
		List<User> listUser = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT * FROM User WHERE id !=" + id + ";";
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
