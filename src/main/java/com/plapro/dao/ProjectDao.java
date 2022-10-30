package com.plapro.dao;

import java.util.List;

import com.plapro.beans.Group;
import com.plapro.beans.Project;
import com.plapro.beans.User;

public interface ProjectDao {
	void addProject(Project p);
	Project getProject(String id);
	String getLastIdProject();
	void addUser(String idProject, String idUser, String idGroup);
	List<Project> listProjectUser(String idUser);
	List<User> getListUser(int idProject);
	void editProject(Project p);
	int countProjectMembers(int idProject);
	User lastActiveMember(int idProject);
	int idMostActiveMember(int idProject);
	List<User> getOtherUser(int idProject);
	List<User> getProjectUsersExceptConnected(int idProject, int idUser);
	List<Group> getAllGroups();
	void removeUser(int idProject, int idUser);
}
