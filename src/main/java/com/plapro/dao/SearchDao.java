package com.plapro.dao;

import java.util.List;

import com.plapro.beans.Project;
import com.plapro.beans.User;

public interface SearchDao {
	List<Project> searchProject(String search);
	List<User> searchUser(String search);
}
