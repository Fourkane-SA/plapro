package com.plapro.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.Activity;
import com.plapro.beans.Chart;
import com.plapro.beans.Project;
import com.plapro.beans.User;
import com.plapro.dao.AccountDao;
import com.plapro.dao.ActivityDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.NotificationsDao;
import com.plapro.dao.ProjectDao;
import com.plapro.dao.TaskDao;

/**
 * Servlet implementation class ProjectDashboard
 */
@WebServlet("/ProjectDashboard")
public class ProjectDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectdao;
	private NotificationsDao notificationsdao;
	private ActivityDao activitydao;
	private TaskDao taskdao;
	private AccountDao accountdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.projectdao = daoFactory.projectDao();
		this.notificationsdao = daoFactory.notifiationsDao();
		this.activitydao = daoFactory.activityDao();
		this.taskdao = daoFactory.taskDao();
		this.accountdao = daoFactory.accountDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		Project p = projectdao.getProject(request.getParameter("id"));
		
		List<Integer> idUpcommingTasks = taskdao.listUpcommingEndTask(p.getId());
		List<com.plapro.beans.Task> upcommingTasks = new ArrayList<com.plapro.beans.Task>();
		for(int i=0; i<idUpcommingTasks.size(); i++) {
			upcommingTasks.add(taskdao.getTaskById(idUpcommingTasks.get(i)));
			upcommingTasks.get(i).setDateEndToString();
		}
		
		request.setAttribute("project", p);
		if (!notificationsdao.isAllNotificationsSeen(u.getId()))
			request.setAttribute("notifications", "unread");
		List<Activity> a = activitydao.showActivityProject(p.getId());
		for(int i=0; i<a.size(); i++) {
			a.get(i).setCreatedAtToString();
		}
		
		List<Integer> idTasksLate = taskdao.listLateTasks(p.getId());
		List<com.plapro.beans.Task> tasksLate = new ArrayList<com.plapro.beans.Task>();
		for(int i=0; i<idTasksLate.size(); i++) {
			tasksLate.add(taskdao.getTaskById(idTasksLate.get(i)));
			tasksLate.get(i).setDateEndToString();
		}
		
		int idMostActiveMember = projectdao.idMostActiveMember(p.getId());
		
		int progress = taskdao.getProjectProgress(p.getId());
		int r = 255 * (1 - progress / 100);
		int g = 255 * progress / 100;
		request.setAttribute("r", r);
		request.setAttribute("g", g);
		
		Chart c = activitydao.getChartActivity(p.getId());
		request.setAttribute("activity", a);
		request.setAttribute("n", taskdao.countTaskProject(p.getId()));
		request.setAttribute("unfinish", taskdao.countUnfinishTaskProject(p.getId()));
		request.setAttribute("unstarted", taskdao.countUnstartedTaskProject(p.getId()));
		request.setAttribute("dataPoints", taskdao.getDataPoints(p.getId()));
		request.setAttribute("dataPoints2", activitydao.getDataPoints(c));
		request.setAttribute("finish", taskdao.countFinishTaskProject(p.getId()));
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("upcommingTasks", upcommingTasks);
		request.setAttribute("tasksLate", tasksLate);
		request.setAttribute("nMembers", projectdao.countProjectMembers(p.getId()));
		request.setAttribute("lastActiveMember", projectdao.lastActiveMember(p.getId()));
		request.setAttribute("mostActiveMember", accountdao.getUser(String.valueOf(idMostActiveMember)));
		request.setAttribute("progress", progress);
		this.getServletContext().getRequestDispatcher("/WEB-INF/projectdashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
