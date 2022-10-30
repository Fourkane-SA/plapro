package com.plapro.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plapro.dao.DaoFactory;
import com.plapro.dao.TaskDao;

/**
 * Servlet implementation class TasksToDo
 */
@WebServlet("/TasksToDo")
public class TasksToDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDao taskdao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TasksToDo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.taskdao = daoFactory.taskDao();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProject = Integer.valueOf(request.getParameter("id"));
		List<Integer> idTasksToDo = taskdao.getTaskToDo(idProject);
		List<com.plapro.beans.Task> TasksToDo= new ArrayList<com.plapro.beans.Task>();
		for(int i=0; i<idTasksToDo.size(); i++) {
			com.plapro.beans.Task t = taskdao.getTaskById(idTasksToDo.get(i));
			TasksToDo.add(t);
		}
		request.setAttribute("TasksToDo", TasksToDo);
		this.getServletContext().getRequestDispatcher("/WEB-INF/taskstodo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
