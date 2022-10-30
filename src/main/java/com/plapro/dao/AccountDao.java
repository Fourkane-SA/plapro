package com.plapro.dao;

import java.util.List;

import com.plapro.beans.User;
public interface AccountDao {
	boolean verifyRegister(User u);//Verify if the register form is correct and add an user in the database
	boolean verifyLogin(User u);//Verify if the pseudo / password is correct
	boolean isExist(String pseudo);//Return true if the user exists
	String getId(String pseudo);//Return the id of a user
	User getUser(String id);//Return the user with the id in parameter
	boolean isAvailableEmail(String email);
	boolean isAvailablePseudo(String pseudo);
	boolean isAvailable(User u);//Verify if the email or the pseudo is already in the database
	void addUser(User u);//Add a user in the database once isExist return true
	int nbProject(String id);
	String updateUser(User user, User newUser, String password);
	List<User> listAllUserWithoutLogin(int id);
}
