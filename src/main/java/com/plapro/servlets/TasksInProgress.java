package com.plapro.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plapro.dao.DaoFactory;
import com.plapro.dao.TaskDao;

/**
 * Servlet implementation class TasksInProgress
 */
@WebServlet("/TasksInProgress")
public class TasksInProgress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDao taskdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TasksInProgress() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.taskdao = daoFactory.taskDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProject = Integer.valueOf(request.getParameter("id"));
		List<Integer> idInProgressTasks = taskdao.getTaskInProgress(idProject);
		List<com.plapro.beans.Task> TasksInProgress = new ArrayList<com.plapro.beans.Task>();
		for(int i=0; i<idInProgressTasks.size(); i++) {
			com.plapro.beans.Task t = taskdao.getTaskById(idInProgressTasks.get(i));
			TasksInProgress.add(t);
		}
		request.setAttribute("TasksInProgress", TasksInProgress);
		this.getServletContext().getRequestDispatcher("/WEB-INF/tasksinprogress.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
