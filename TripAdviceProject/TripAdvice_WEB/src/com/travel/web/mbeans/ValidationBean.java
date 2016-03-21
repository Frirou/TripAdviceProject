package com.travel.web.mbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.esprit.persistence.User;
import com.esprit.service.UserServiceLocal;

@RequestScoped
@ManagedBean
public class ValidationBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserServiceLocal userServiceLocal;
	
	@ManagedProperty(value = "#{param.id}")
	private Long id;
	

	private boolean valide = false;

	private static int i = 0;
	
	public ValidationBean() {
		super();
	}

	public boolean isValide() {
		return valide;
	}
	
	public void validateAccount()
	{
		try {
			if(i==0)
			{
				System.out.println("id : "+id.intValue());
				User user =userServiceLocal.getUserById(id.intValue());
				
				user.setValide(true);
				System.out.println(1);
				userServiceLocal.updateUser(user);
				valide=true;
				 
			}
			i++;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("page appellée après inscription");
		}
		
	}
	
	
	

	public void setValide(boolean valide) {
		this.valide = valide;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
