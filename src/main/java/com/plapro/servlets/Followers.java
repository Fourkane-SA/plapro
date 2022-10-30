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

import com.plapro.beans.User;
import com.plapro.dao.AccountDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.FollowDao;
import com.plapro.dao.NotificationsDao;

/**
 * Servlet implementation class followers
 */
@WebServlet("/Followers")
public class Followers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FollowDao followDao;
	private AccountDao accountDao;
	private NotificationsDao notificationsdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Followers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.accountDao = daoFactory.accountDao();
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
		String id = request.getParameter("id");
		List<Integer> idFollowers = followDao.listIdFollowers(id);
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < idFollowers.size(); i++) {
			users.add(accountDao.getUser(idFollowers.get(i).toString()));
			users.get(i).setCreatedAtToString();
		}
		request.setAttribute("users", users);
		if (!notificationsdao.isAllNotificationsSeen(u.getId()))
			request.setAttribute("notifications", "unread");
		this.getServletContext().getRequestDispatcher("/WEB-INF/followers.jsp").forward(request, response);
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
