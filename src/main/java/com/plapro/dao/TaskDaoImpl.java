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
import com.plapro.beans.Task;

public class TaskDaoImpl implements TaskDao {
	private DaoFactory daoFactory;

	TaskDaoImpl(DaoFactory daofactory) {
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
	public void addTask(Task t) {
		Connection connection = null;
		Statement statement = null;
		String req = "INSERT INTO taskProject (name, start, end, progress, description, idGroup, finish, idUser, idProject) VALUES ('"
				+ t.getName() + "', '" + t.getStart() + "', '" + t.getEnd() + "', '" + t.getProgress() + "', '"
				+ t.getDescription() + "', '" + t.getIdGroup() + "', '" + t.getFinish() + "', '" + t.getIdUser()
				+ "', '" + t.getIdProject() + "');";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int countTaskProject(int idProject) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n " + "FROM taskProject " + "WHERE idProject = " + idProject + ";";
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
	public int countFinishTaskProject(int idProject) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n " + "FROM taskProject " + "WHERE idProject = " + idProject
				+ " AND progress = 100;";
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
	public int countUnfinishTaskProject(int idProject) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n " + "FROM taskProject " + "WHERE idProject = " + idProject
				+ " AND progress != 100 AND progress != 0;";
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
	public Task getTaskById(int id) {
		Task t = new Task();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT *" + "FROM taskProject " + "WHERE id = " + id + ";";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				t.setDescription(result.getString("description"));
				t.setEnd(result.getString("end"));
				t.setFinish(result.getString("finish"));
				t.setId(result.getInt("id"));
				t.setIdGroup(result.getInt("idGroup"));
				t.setIdProject(result.getInt("idProject"));
				t.setIdUser(result.getInt("idUser"));
				t.setName(result.getString("name"));
				t.setProgress(result.getInt("progress"));
				t.setStart(result.getString("start"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public int getLastId() {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT MAX(id) as n FROM taskProject;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				n = (result.getInt("n"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int countUnstartedTaskProject(int idProject) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n " + "FROM taskProject " + "WHERE idProject = " + idProject
				+ " AND progress = 0;";
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
	public String getDataPoints(int idProject) {
		Gson gsonObj = new Gson();
		Map<Object, Object> map = null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

		if(countUnfinishTaskProject(idProject) > 0) {
			map = new HashMap<Object, Object>();
			map.put("label", "En cours");
			map.put("y", countUnfinishTaskProject(idProject));
			list.add(map);
		}
		if(countFinishTaskProject(idProject) > 0) {
			map = new HashMap<Object, Object>();
			map.put("label", "Terminée");
			map.put("y", countFinishTaskProject(idProject));
			list.add(map);
		}
		if(countUnstartedTaskProject(idProject) > 0) {
			map = new HashMap<Object, Object>();
			map.put("label", "A faire");
			map.put("y", countUnstartedTaskProject(idProject));
			list.add(map);
		}
		
		return gsonObj.toJson(list);
	}

	@Override
	public List<Integer> getFinishedTask(int idProject) {
		List<Integer> idTasks = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT id FROM taskProject WHERE idProject = " + idProject + " AND progress = 100;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				idTasks.add(result.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idTasks;
	}

	@Override
	public List<Integer> getTaskInProgress(int idProject) {
		List<Integer> idTasks = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT id FROM taskProject WHERE idProject = " + idProject
				+ " AND progress > 0 AND progress < 100;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				idTasks.add(result.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idTasks;
	}

	@Override
	public List<Integer> getTaskToDo(int idProject) {
		List<Integer> idTasks = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT id FROM taskProject WHERE idProject = " + idProject + " AND progress = 0;";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				idTasks.add(result.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idTasks;
	}

	@Override
	public void editTask(Task t) {
		Connection connection = null;
		Statement statement = null;
		String req = "UPDATE taskProject SET name='"
				+ t.getName() + "' , start='"
				+ t.getStart() + "', end='"
				+ t.getEnd() + "', progress='"
				+ t.getProgress() + "', description='"
				+ t.getDescription() + "', idGroup='"
				+ t.getIdGroup() + "', finish='"
				+ t.getFinish() + "' WHERE id="
				+ t.getId() + ";";
		try {
			connection = loadDatabase();
			statement = connection.createStatement();
			statement.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int countTasksToDoLate(int idProject) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM `taskProject` WHERE DATEDIFF(DATE(NOW()),end) >0 AND progress = 0 AND idProject = " + idProject + ";";
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
	public int countTasksInProgressLate(int idProject) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM `taskProject` WHERE DATEDIFF(DATE(NOW()),end) >0 AND progress < 100 AND progress > 0 AND idProject = " + idProject + ";";
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
	public int countTasksCompletedLate(int idProject) {
		int n = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT COUNT(*) AS n FROM `taskProject` WHERE DATEDIFF(finish,end) >0 AND idProject =" + idProject + ";";
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
	public List<Integer> listUpcommingEndTask(int idProject) {
		List<Integer> idTasks = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT id FROM taskProject WHERE idProject="
				+ idProject + " AND end >= DATE(NOW()) AND progress < 100 ORDER BY end";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				idTasks.add(result.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idTasks;
	}

	@Override
	public List<Integer> listLateTasks(int idProject) {
		List<Integer> idTasks = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String req = "SELECT * FROM taskProject WHERE idProject = "
				+ idProject + " AND end < DATE(NOW()) AND progress < 100";
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(req);
			while (result.next()) {
				idTasks.add(result.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idTasks;
	}

	@Override
	public int getProjectProgress(int idProject) {
		float nbTasks = countTaskProject(idProject);
		float finishedTasks = countFinishTaskProject(idProject);
		if(nbTasks == 0)
			return 0;
		return (int) (finishedTasks / nbTasks * 100);
	}
}
