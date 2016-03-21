package com.esprit.service;

import java.util.List;

import javax.ejb.Local;

import com.esprit.persistence.Personne;
import com.esprit.persistence.User;

@Local
public interface UserServiceLocal {
	void createUser(User user);
	List<User> findAllUsers();
	List<Personne> findAllUsers1();

	User authenticate(String login, String password);
	boolean loginExists(String login);
	public User findUserByLogin(String login) ;
	public byte[] findPictureByPersonneId(int idPersonne);
	public User findUserById(int id);
	public List<Personne> findUsersNotMe(int iduser);
	void updateuser(User user) ;
	
	int createUser1(User user);
	void updateUser(User user);

	User getUserById(int id);



}
