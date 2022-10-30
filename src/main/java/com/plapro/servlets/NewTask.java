package com.plapro.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.Activity;
import com.plapro.beans.Task;
import com.plapro.beans.User;
import com.plapro.dao.ActivityDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.TaskDao;

/**
 * Servlet implementation class NewTask
 */
@WebServlet("/NewTask")
public class NewTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDao taskdao;
	private ActivityDao activitydao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.taskdao = daoFactory.taskDao();
		this.activitydao = daoFactory.activityDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		this.getServletContext().getRequestDispatcher("/WEB-INF/newtask.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		Task task = new Task();
		task.setDescription(request.getParameter("description"));
		task.setEnd(request.getParameter("end"));
		task.setName(request.getParameter("name"));
		task.setIdGroup(Integer.valueOf(request.getParameter("group")));
		task.setProgress(Integer.valueOf(request.getParameter("state")));
		task.setStart(request.getParameter("start"));
		if (task.getProgress() == 100)
			task.setFinish(request.getParameter("finish"));
		task.setIdProject(Integer.valueOf(request.getParameter("id")));
		task.setIdUser(u.getId());
		taskdao.addTask(task);
		task.setId(taskdao.getLastId());

		Activity a = new Activity();
		a.setIdProject(task.getIdProject());
		a.setIdUser(u.getId());
		a.setLink("task?id=" + task.getId());
		a.setText("La tache " + task.getName() + " a été créée par " + u.getPseudo());
		a.setType("newTask");
		activitydao.addActivity(a);
		
		request.setAttribute("newTask", task);
		doGet(request, response);
	}

}
