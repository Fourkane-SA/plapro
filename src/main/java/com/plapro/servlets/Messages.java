package com.plapro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.Message;
import com.plapro.beans.User;
import com.plapro.dao.AccountDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.MessageDao;
import com.plapro.dao.NotificationsDao;

/**
 * Servlet implementation class Messages
 */
@WebServlet("/Messages")
public class Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationsDao notificationsdao;
	private MessageDao messagedao;
	private AccountDao accountdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Messages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.notificationsdao = daoFactory.notifiationsDao();
		this.messagedao = daoFactory.messageDao();
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
		if (!notificationsdao.isAllNotificationsSeen(u.getId()))
			request.setAttribute("notifications", "unread");
		List<User> listUser = accountdao.listAllUserWithoutLogin(u.getId());
		List<Message> recentsMessages = messagedao.getLastConversation(u.getId());
		request.setAttribute("users", listUser);
		request.setAttribute("messages", recentsMessages);
		this.getServletContext().getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
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
