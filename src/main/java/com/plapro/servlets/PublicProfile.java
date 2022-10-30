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
 * Servlet implementation class PublicProfile
 */
@WebServlet("/PublicProfile")
public class PublicProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao;
	private ProjectDao projectdao;
	private FollowDao followDao;
	private NotificationsDao notificationsDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.accountDao = daoFactory.accountDao();
		this.projectdao = daoFactory.projectDao();
		this.followDao = daoFactory.followDao();
		this.notificationsDao = daoFactory.notifiationsDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("user");
		User user = new User();
		String id = request.getParameter("id");
		user = accountDao.getUser(id);
		if (id.equals(String.valueOf(userLogin.getId()))) {
			request.setAttribute("userProfile", true);
			this.getServletContext().getRequestDispatcher("/WEB-INF/publicprofile.jsp").forward(request, response);
		}
		if (request.getParameter("follow") != null) {
			followDao.follow(String.valueOf(userLogin.getId()), id);
			String link = "publicprofile?id=" + userLogin.getId();
			String text = userLogin.getPseudo() + " a commencé à vous suivre";
			String type = "newFollow";
			notificationsDao.addNotification(id, link, text, type);
		} else if (request.getParameter("unfollow") != null) {
			followDao.unfollow(String.valueOf(userLogin.getId()), id);
		}

		if (followDao.isFollowing(String.valueOf(userLogin.getId()), id))
			request.setAttribute("isFollowing", true);
		else
			request.setAttribute("isFollowing", false);

		int nbFollowers = followDao.nbFollowers(id);
		int nbFollowing = followDao.nbFollowing(id);
		int nbProject = accountDao.nbProject(id);
		List<Project> projects = projectdao.listProjectUser(id);
		for(int i=0; i<projects.size(); i++) {
			projects.get(i).setCreationDateToString();
			projects.get(i).setUpdateTimeToString();
		}
		user.setCreatedAtToString();

		if (!notificationsDao.isAllNotificationsSeen(userLogin.getId()))
			request.setAttribute("notifications", "unread");

		request.setAttribute("user", user);
		request.setAttribute("following", nbFollowing);
		request.setAttribute("followers", nbFollowers);
		request.setAttribute("project", nbProject);
		request.setAttribute("projects", projects);
		this.getServletContext().getRequestDispatcher("/WEB-INF/publicprofile.jsp").forward(request, response);
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
