package com.plapro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.Project;
import com.plapro.beans.User;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.NotificationsDao;
import com.plapro.dao.ProjectDao;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationsDao notificationsdao;
	private ProjectDao projectdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.projectdao = daoFactory.projectDao();
		this.notificationsdao = daoFactory.notifiationsDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		List<Project> projects = projectdao.listProjectUser(String.valueOf(u.getId()));
		for(int i=0; i<projects.size(); i++) {
			projects.get(i).setCreationDateToString();
			projects.get(i).setUpdateTimeToString();
		}
		
		request.setAttribute("projects", projects);
		if (!notificationsdao.isAllNotificationsSeen(u.getId()))
			request.setAttribute("notifications", "unread");
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
