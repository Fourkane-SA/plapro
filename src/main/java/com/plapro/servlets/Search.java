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
import com.plapro.dao.SearchDao;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationsDao notificationsDao;
	private SearchDao searchDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.notificationsDao = daoFactory.notifiationsDao();
		this.searchDao = daoFactory.searchDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("user");

		if (!notificationsDao.isAllNotificationsSeen(userLogin.getId()))
			request.setAttribute("notifications", "unread");
		this.getServletContext().getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Project> listProject = searchDao.searchProject(request.getParameter("search"));
		for(int i=0; i<listProject.size(); i++) {
			listProject.get(i).setCreationDateToString();
			listProject.get(i).setUpdateTimeToString();
		}
		
		List<User> listUser = searchDao.searchUser(request.getParameter("search"));
		for(int i=0; i<listUser.size(); i++) {
			listUser.get(i).setCreatedAtToString();
		}
		request.setAttribute("projects", listProject);
		request.setAttribute("users", listUser);
		request.setAttribute("search", true);
		doGet(request, response);
	}

}
