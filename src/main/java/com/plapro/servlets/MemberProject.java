package com.plapro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plapro.beans.Project;
import com.plapro.beans.User;
import com.plapro.dao.AccountDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.ProjectDao;

/**
 * Servlet implementation class MemberProject
 */
@WebServlet("/MemberProject")
public class MemberProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectdao;
	private AccountDao accountdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberProject() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.projectdao = daoFactory.projectDao();
		this.accountdao = daoFactory.accountDao();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Project p = projectdao.getProject(String.valueOf(id));
		int nbMember = projectdao.countProjectMembers(p.getId());
		int idMostActiveMember = projectdao.idMostActiveMember(p.getId());
		List<User> users = projectdao.getListUser(p.getId());
		
		request.setAttribute("nMembers", nbMember);
		request.setAttribute("lastActiveMember", projectdao.lastActiveMember(p.getId()));
		request.setAttribute("mostActiveMember", accountdao.getUser(String.valueOf(idMostActiveMember)));
		request.setAttribute("users", users);
		request.setAttribute("id", id);
		this.getServletContext().getRequestDispatcher("/WEB-INF/memberproject.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
