package com.plapro.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plapro.beans.Activity;
import com.plapro.beans.Project;
import com.plapro.beans.User;
import com.plapro.dao.ActivityDao;
import com.plapro.dao.DaoFactory;
import com.plapro.dao.ProjectDao;

/**
 * Servlet implementation class EditProject
 */
@WebServlet("/EditProject")
public class EditProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectdao;
	private ActivityDao activitydao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProject() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.projectdao = daoFactory.projectDao();
		this.activitydao = daoFactory.activityDao();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Project p = projectdao.getProject(id);
		request.setAttribute("project", p);
		this.getServletContext().getRequestDispatcher("/WEB-INF/editproject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		
		Project p = new Project();
		p.setDescription(request.getParameter("description"));
		p.setId(Integer.valueOf(request.getParameter("id")));
		p.setName(request.getParameter("name"));
		p.setShortDescription(request.getParameter("shortdescription"));
		p.setVisibility(request.getParameter("visibility"));
		p.setStatus(request.getParameter("status"));
		projectdao.editProject(p);
		request.setAttribute("error", "no");
		
		Activity a = new Activity();
		a.setIdProject(p.getId());
		a.setLink("projectsetting?id=" + p.getId());
		a.setText("Les informations du projet " + p.getName() + " ont été mis à jour par " + u.getPseudo());
		a.setType("editProject");
		a.setIdUser(u.getId());
		activitydao.addActivity(a);
		doGet(request, response);
	}

}
