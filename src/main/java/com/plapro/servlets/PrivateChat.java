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
import com.plapro.dao.DaoFactory;
import com.plapro.dao.MessageDao;
import com.plapro.dao.NotificationsDao;

/**
 * Servlet implementation class PrivateChat
 */
@WebServlet("/PrivateChat")
public class PrivateChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationsDao notificationsdao;
	private MessageDao messagedao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrivateChat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.notificationsdao = daoFactory.notifiationsDao();
		this.messagedao = daoFactory.messageDao();
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
		request.setAttribute("idUserChat", request.getParameter("id"));
		int idUserChat = Integer.parseInt(request.getParameter("id"));
		int idConversation = messagedao.isPrivateConversationExist(u.getId(), idUserChat);
		if (idConversation == -1) {
			messagedao.createNewConversation(u.getId(), idUserChat);
			idConversation = messagedao.getLastConversation();
		}
		List<Message> conversation = messagedao.getConversation(idConversation);
		String userPrivateConversation = messagedao.getOtherUserPrivateConversation(idConversation, u.getId());
		conversation = messagedao.groupMessages(conversation);
		for(int i=0; i<conversation.size(); i++) {
			conversation.get(i).setCreatedAtToString();
		}
		request.setAttribute("conversation", conversation);
		request.setAttribute("userPrivateConversation", userPrivateConversation);
		this.getServletContext().getRequestDispatcher("/WEB-INF/privatechat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		int idUserSender = u.getId();
		int idUserReceiver = Integer.parseInt(request.getParameter("id"));
		int idConversation = messagedao.isPrivateConversationExist(idUserSender, idUserReceiver);
		String text = request.getParameter("text");
		messagedao.addMessage(idUserSender, idConversation, text);
		doGet(request, response);
	}

}
