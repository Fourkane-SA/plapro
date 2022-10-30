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
 * Servlet implementation class AddMemberProject
 */
@WebServlet("/AddMemberProject")
public class AddMemberProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectdao;
	private ActivityDao activitydao;
	private AccountDao accountdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMemberProject() {
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
		int id = Integer.valueOf(request.getParameter("id"));
		request.setAttribute("groups", projectdao.getAllGroups());
		request.setAttribute("users", projectdao.getOtherUser(id));
		request.setAttribute("id", id);
		this.getServletContext().getRequestDispatcher("/WEB-INF/addmemberproject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String idUser = request.getParameter("pseudo");
		String idGroup = request.getParameter("group");
		String idProject = request.getParameter("id");
		User newUser = accountdao.getUser(idUser);
		Activity a = new Activity();
		a.setIdProject(Integer.valueOf(idProject));
		a.setIdUser(u.getId());
		a.setLink("publicprofile?id=" + newUser.getId());
		a.setPseudoUser(u.getPseudo());
		a.setText(newUser.getPseudo() + " a été ajouté au projet par " + u.getPseudo());
		a.setType("newMemberProject");
		
		projectdao.addUser(idProject, idUser, idGroup);
		activitydao.addActivity(a);
		
		request.setAttribute("addUser", true);
		doGet(request, response);
	}

}
