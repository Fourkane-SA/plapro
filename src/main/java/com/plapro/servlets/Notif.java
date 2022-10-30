package com.plapro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.Notifications;
import com.plapro.beans.User;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.NotificationsDao;

/**
 * Servlet implementation class Notif
 */
@WebServlet("/Notif")
public class Notif extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationsDao notificationsDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Notif() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
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
		List<Notifications> notifs = notificationsDao.getListNotifications(String.valueOf(userLogin.getId()));
		for(int i=0; i<notifs.size(); i++) {
			notifs.get(i).setCreatedAtToString();
		}
		request.setAttribute("notifs", notifs);
		notificationsDao.markNotificationsAsSeen(String.valueOf(userLogin.getId()));
		this.getServletContext().getRequestDispatcher("/WEB-INF/notif.jsp").forward(request, response);
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
