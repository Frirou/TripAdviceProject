package com.travel.web.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.esprit.persistence.User;
import com.esprit.service.UserServiceLocal;


@ManagedBean(name="authBean")
@SessionScoped
public class AuthenticationBean implements Serializable{
	
	private static final long serialVersionUID = -6916676537171647179L;
	
	@EJB
	private UserServiceLocal authenticationServiceLocal;
	
	//our model
	private User user;
	private boolean loggedIn;
	private List<User> users;
	//
	
	public AuthenticationBean() {
	}
	
	@PostConstruct
	public void init(){
		user = new User();
		users=authenticationServiceLocal.findAllUsers();
		setLoggedIn(false);
	}
	
	public String doLogin(){
		String navigateTo = null;
		User found = authenticationServiceLocal.authenticate(user.getLogin(), user.getPassword());
		if (found != null) {
			user = found;
			loggedIn = true;
			
			navigateTo = "/welcome?face-redirect=true";
	}
			else {
		FacesContext.getCurrentInstance()
			.addMessage("login_form:login_submit", new FacesMessage("Bad Credentials!"));
	}
		return navigateTo;
	}

	public String doLogout(){
		String navigateTo = null;
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		init();
		navigateTo = "/login?faces-redirect=true";
		return navigateTo;
	}
	public User getUser() {
		return user;
	}
	
	public String doRegister(){
		String navigateTo = null;

		if(authenticationServiceLocal.createUser1(user)!=0 )
		{
			
			Email email = new Email();
			email.sendConfirmationMail(user);
		}
		navigateTo = "/login?faces-redirect=true";
		return navigateTo;

	}

	
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
