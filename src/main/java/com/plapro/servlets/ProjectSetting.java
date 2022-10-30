package com.plapro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plapro.beans.Project;
import com.plapro.beans.User;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.ProjectDao;
import com.plapro.dao.TaskDao;

/**
 * Servlet implementation class ProjectSetting
 */
@WebServlet("/ProjectSetting")
public class ProjectSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectdao;
	private TaskDao taskdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectSetting() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.projectdao = daoFactory.projectDao();
		this.taskdao = daoFactory.taskDao();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.valueOf(request.getParameter("id"));
		Project p = projectdao.getProject(String.valueOf(projectId));
		p.setCreationDateToString();
		p.setUpdateTimeToString();
		int taskFinished = taskdao.countFinishTaskProject(projectId);
		int currentTasks = taskdao.countUnfinishTaskProject(projectId);
		int tasksToDo = taskdao.countUnstartedTaskProject(projectId);
		int numberOfTasks = taskFinished + currentTasks + tasksToDo;
		int countTasksCompletedLate = taskdao.countTasksCompletedLate(projectId);
		int countTasksInProgressLate = taskdao.countTasksInProgressLate(projectId);
		int countTasksToDoLate = taskdao.countTasksToDoLate(projectId);
		int numberOfTasksLate = countTasksCompletedLate + countTasksInProgressLate + countTasksToDoLate;
		List<User> users = projectdao.getListUser(projectId);
		
		request.setAttribute("project", p);
		request.setAttribute("taskFinished", taskFinished);
		request.setAttribute("currentTasks", currentTasks);
		request.setAttribute("tasksToDo", tasksToDo);
		request.setAttribute("n", numberOfTasks);
		request.setAttribute("countTasksCompletedLate", countTasksCompletedLate);
		request.setAttribute("countTasksInProgressLate", countTasksInProgressLate);
		request.setAttribute("countTasksToDoLate", countTasksToDoLate);
		request.setAttribute("nLate", numberOfTasksLate);
		request.setAttribute("users", users);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/projectsetting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
