package com.plapro.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.Activity;
import com.plapro.beans.User;
import com.plapro.dao.ActivityDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.TaskDao;

/**
 * Servlet implementation class EditTask
 */
@WebServlet("/EditTask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDao taskdao;
	private ActivityDao activitydao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTask() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.taskdao = daoFactory.taskDao();
		this.activitydao = daoFactory.activityDao();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		com.plapro.beans.Task t = taskdao.getTaskById(id);
		request.setAttribute("task", t);
		this.getServletContext().getRequestDispatcher("/WEB-INF/edittask.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.plapro.beans.Task task = new com.plapro.beans.Task();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		task.setDescription(request.getParameter("description"));
		task.setEnd(request.getParameter("end"));
		task.setName(request.getParameter("name"));
		task.setIdGroup(Integer.valueOf(request.getParameter("group")));
		task.setProgress(Integer.valueOf(request.getParameter("state")));
		task.setStart(request.getParameter("start"));
		if (task.getProgress() == 100)
			task.setFinish(request.getParameter("finish"));
		task.setId(Integer.valueOf(request.getParameter("id")));
		task.setIdProject(taskdao.getTaskById(task.getId()).getIdProject());
		taskdao.editTask(task);
		
		Activity a = new Activity();
		a.setIdProject(task.getIdProject());
		a.setIdUser(u.getId());
		a.setLink("task?id=" + task.getId());
		a.setText("La tache " + task.getName() + " a été mis à jour par " + u.getPseudo());
		a.setType("editTask");
		activitydao.addActivity(a);
		
		request.setAttribute("editTask", task);
		doGet(request, response);
	}

}
