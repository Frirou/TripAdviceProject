package com.travel.web.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import com.esprit.persistence.Event;
import com.esprit.persistence.Personne;
import com.esprit.service.EventServiceLocal;
import com.esprit.service.UserServiceLocal;

@ManagedBean
@ViewScoped
public class UserBean {

	@EJB
	private UserServiceLocal userlocal;
	private EventServiceLocal eventServiceLocal;
	private Personne pers = new Personne();
	private List<Personne> personnes;
	private List<Personne> fileteredUsers;
	private boolean formDisplayed = false;
	private Mail mail = new Mail();
	private Event event = new Event();
	private List<Event> events;

	public UserBean() {

	}

	@PostConstruct
	public void init() {

		personnes = userlocal.findAllUsers1();

	}

	public List<Personne> getEventsNotMe(int iduser) {

		personnes = userlocal.findUsersNotMe(iduser);
		return personnes;
	}

	public void envoiMail() {

		
		
	
			mail.setCorp("hi , " + pers.getNom() + " " + pers.getPrenom()
					+ " Ask you if you like to participate in his new Event ");
		mail.setTo(pers.getMail());
		mail.send();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Confirmation Msg", "Invitation sent.");
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	

	public void onRowSelect(SelectEvent event) {

		formDisplayed = true;
	}

	
	public Personne getPers() {
		return pers;
	}

	public void setPers(Personne pers) {
		this.pers = pers;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Personne> getFileteredUsers() {
		return fileteredUsers;
	}

	public void setFileteredUsers(List<Personne> fileteredUsers) {
		this.fileteredUsers = fileteredUsers;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public List<Event> getEvents() {

		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
public void doSaveOrUpdate(){
		
		AuthenticationBean me = (AuthenticationBean)
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
		
		userlocal.updateuser(me.getUser());
		
		formDisplayed =false;
		
	}

public void handleFileUpload(FileUploadEvent event) {
	AuthenticationBean me = (AuthenticationBean)
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
	me.getUser().setImg(event.getFile().getContents());

}
	
	
	
	

}
