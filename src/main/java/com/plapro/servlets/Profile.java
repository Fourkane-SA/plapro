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
import com.plapro.dao.AccountDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.FollowDao;
import com.plapro.dao.NotificationsDao;
import com.plapro.dao.ProjectDao;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao;
	private ProjectDao projectdao;
	private FollowDao followDao;
	private NotificationsDao notificationsdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.accountDao = daoFactory.accountDao();
		this.projectdao = daoFactory.projectDao();
		this.followDao = daoFactory.followDao();
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
		String id = String.valueOf(u.getId());
		int nbFollowers = followDao.nbFollowers(id);
		int nbFollowing = followDao.nbFollowing(id);
		int nbProject = accountDao.nbProject(id);
		u.setCreatedAtToString();
		String createdAt = u.getCreatedAt();
		List<Project> projects = projectdao.listProjectUser(id);
		for(int i=0; i<projects.size(); i++) {
			projects.get(i).setCreationDateToString();
			projects.get(i).setUpdateTimeToString();
		}
		if (!notificationsdao.isAllNotificationsSeen(u.getId()))
			request.setAttribute("notifications", "unread");

		request.setAttribute("following", nbFollowing);
		request.setAttribute("followers", nbFollowers);
		request.setAttribute("project", nbProject);
		request.setAttribute("projects", projects);
		request.setAttribute("createdAt", createdAt);
		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
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
