package com.plapro.servlets;

import java.io.IOException;
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
 * Servlet implementation class NewProject
 */
@WebServlet("/NewProject")
public class NewProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectdao;
	private NotificationsDao notificationsdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewProject() {
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
		User user = (User) session.getAttribute("user");
		if (!notificationsdao.isAllNotificationsSeen(user.getId()))
			request.setAttribute("notifications", "unread");
		this.getServletContext().getRequestDispatcher("/WEB-INF/newproject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Project p = new Project();
		p.setName(request.getParameter("name"));
		p.setShortDescription(request.getParameter("shortdescription"));
		p.setVisibility(request.getParameter("visibility"));
		p.setStatus("En cours");
		projectdao.addProject(p);
		String idProject = projectdao.getLastIdProject();
		int idUser;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		idUser = user.getId();
		projectdao.addUser(idProject, String.valueOf(idUser), "1");
		request.setAttribute("error", "no");
		String link = "projectdashboard?id=" + p.getId();
		String text = "Le projet " + p.getName() + " a été créé avec succès";
		String type = "newProject";
		notificationsdao.addNotification(String.valueOf(idUser), link, text, type);
		doGet(request, response);
	}

}
