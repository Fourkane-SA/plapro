package com.plapro.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.User;
import com.plapro.dao.AccountDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.NotificationsDao;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao;
	private NotificationsDao notificationsdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.accountDao = daoFactory.accountDao();
		this.notificationsdao = daoFactory.notifiationsDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/editprofile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User updateUser = new User();
		updateUser.setBirth(request.getParameter("birth"));
		updateUser.setDescription(request.getParameter("description"));
		updateUser.setCreatedAt(u.getCreatedAt());
		updateUser.setEmail(request.getParameter("email"));
		updateUser.setFirstName(request.getParameter("firstname"));
		updateUser.setGender(request.getParameter("gender"));
		updateUser.setId(u.getId());
		updateUser.setLastName(request.getParameter("lastname"));
		updateUser.setPassword(request.getParameter("newpassword"));
		updateUser.setPseudo(request.getParameter("pseudo"));
		String error = accountDao.updateUser(u, updateUser, request.getParameter("password"));
		if (!notificationsdao.isAllNotificationsSeen(u.getId()))
			request.setAttribute("notifications", "unread");
		request.setAttribute("error", error);
		if (error.equals("no"))
			session.setAttribute("user", updateUser);
		doGet(request, response);
	}

}
