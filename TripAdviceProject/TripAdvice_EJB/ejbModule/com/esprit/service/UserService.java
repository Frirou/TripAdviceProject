package com.esprit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.esprit.persistence.Personne;
import com.esprit.persistence.User;

@Stateless
public class UserService implements UserServiceLocal{
	@PersistenceContext
	private EntityManager em;
	
	public UserService() {
	}

	public void createUser(User user) {
		em.persist(user);
	}

	public List<User> findAllUsers() {
		return em.createQuery("select u from User u").getResultList();
	}
	
	
	public User findUserByLogin(String login) {
		User found = null;
		String jpql = "select u from User u where u.login=:login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		try{
			found = (User) query.getSingleResult();
		}catch(Exception ex){
		}
		return found;
		
	}
	
	
	public User findUserById(int id) {
		User found = null;
		String jpql = "select u from User u where u.id=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x",id);
		try{
			found = (User) query.getSingleResult();
		}catch(Exception ex){
		}
		return found;
		
	}

	public User authenticate(String login, String password) {

		User found = null;
		String jpql = "select u from User u where u.login=:login and u.password=:password and u.valide=1";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		query.setParameter("password", password);
		try{
			found = (User) query.getSingleResult();
		}catch(Exception ex){
		}
		return found;
		
	}

	public boolean loginExists(String login) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from User u where u.login=:login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		exists = (Boolean)query.getSingleResult();
		return exists;	}

	public byte[] findPictureByPersonneId(int idPersonne) {

		byte[] picture = null;
		Query query = em.createQuery("select p.img from Personne p where p.id=:x");
		query.setParameter("x", idPersonne);
		try{
			picture =  (byte[]) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no picture");
		}
		return picture;
	}
	
	public List<Personne> findUsersNotMe(int iduser) {
		Query q = em.createQuery("select a from Personne a");	
		List<Personne> mAll = new ArrayList<Personne>();
		List<Personne> mByid = new ArrayList<Personne>();
		mAll=q.getResultList();
		Iterator<Personne> it = mAll.iterator();

		while (it.hasNext()) {
			Personne m= new Personne();
			m=it.next();
			if(m.getId()!=iduser){
				mByid.add(m);
						
			}
	 
		}
		 return mByid;
	}

	public List<Personne> findAllUsers1() {
		return em.createQuery("select u from Personne u").getResultList();
	}


	
	@Override
	public void updateuser(User user) {
		em.merge(user);
		
	}
	
	public int createUser1(User user) {
		em.persist(user);
		em.flush();
		return user.getId();
	}
	
	public void updateUser(User user)
	{
		em.merge(user);
	}

	public User getUserById(int id) {
		return em.find(User.class, id);
	}

	

}
