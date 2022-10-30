package com.plapro.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.Activity;
import com.plapro.beans.User;
import com.plapro.dao.AccountDao;
import com.plapro.dao.ActivityDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.ProjectDao;

/**
 * Servlet implementation class DeleteMemberProject
 */
@WebServlet("/DeleteMemberProject")
public class DeleteMemberProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectdao;
	private ActivityDao activitydao;
	private AccountDao accountdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberProject() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.projectdao = daoFactory.projectDao();
		this.activitydao = daoFactory.activityDao();
		this.accountdao = daoFactory.accountDao();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		int id = Integer.valueOf(request.getParameter("id"));
		
		request.setAttribute("users", projectdao.getProjectUsersExceptConnected(id, u.getId()));
		request.setAttribute("id", id);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/deletememberproject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		int idProject = Integer.valueOf(request.getParameter("id"));
		int idUser = Integer.valueOf(request.getParameter("pseudo"));
		User DeleteUser = accountdao.getUser(String.valueOf(idUser));
		Activity a = new Activity();
		a.setIdProject(Integer.valueOf(idProject));
		a.setIdUser(u.getId());
		a.setLink("publicprofile?id=" + idUser);
		a.setPseudoUser(u.getPseudo());
		a.setText(DeleteUser.getPseudo() + " a été supprimé du projet par " + u.getPseudo());
		a.setType("deleteMemberProject");
		
		activitydao.addActivity(a);
		projectdao.removeUser(idProject, idUser);
		request.setAttribute("deleteUser", true);
		
		doGet(request, response);
	}

}
