package com.travel.web.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.esprit.persistence.Message;
import com.esprit.persistence.Personne;
import com.esprit.persistence.User;
import com.esprit.service.MessageServiceRemote;
import com.esprit.service.UserServiceLocal;


@ManagedBean
@ViewScoped
public class MsgBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private User client = new User();
	private List<User> clients ;
	private boolean listeOfClientsVisibility = false;
	private User selecteClient ;
	private boolean aa = true;
	private Message message = new Message();
	private String txt;
	User user1= new User();
	Date d =new Date();
	
	
	@EJB
	private UserServiceLocal clientservicelocal ;
	
	@EJB
	private MessageServiceRemote messageservicelocal ;
	
	public MsgBean() {
		super();
	}
	

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public List<User> getClients() {
		clients= clientservicelocal.findAllUsers();
		return clients;
	}

	public void setClients(List<User> clients) {
		this.clients = clients;
	}
	
	
	
	public String enableVisibility() {
		setListeOfClientsVisibility(true);
		return "";
	}
	
	public String disableVisibility() {
		setListeOfClientsVisibility(false);
		return "";
	}
	

	public boolean isListeOfClientsVisibility() {
		return listeOfClientsVisibility;
	}

	public void setListeOfClientsVisibility(boolean listeOfClientsVisibility) {
		this.listeOfClientsVisibility = listeOfClientsVisibility;
	}

	


	public User getSelecteClient() {
		return selecteClient;
	}

	public void setSelecteClient(User selecteClient) {
		this.selecteClient = selecteClient;
	}
	
	public List<Message> aficheMessage(int idd)
	{
		if (aa) {
			idd = 1;
			aa = false;
		}
		AuthenticationBean me = (AuthenticationBean)
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
		List<Message> mm = messageservicelocal.getMessageByClents(idd,me.getUser().getId());
		
		return mm;
	}
	
	public List<Personne> friendnotme() { 
		AuthenticationBean me = (AuthenticationBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
		List<Personne> cc= clientservicelocal.findUsersNotMe(me.getUser().getId());
		return cc; }
	
	

	
	public void doNew(){
		message= new Message();
		AuthenticationBean me = (AuthenticationBean)
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
		message.setDate(d);
		message.setpReceive(selecteClient);
		message.setpSend(me.getUser());
		message.setText(txt);
		messageservicelocal.addMessage(message);
		
	}
	
	
	
	public List<User> friend()
	{
		List<User> cc = clientservicelocal.findAllUsers();
		AuthenticationBean me = (AuthenticationBean)
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");	
		cc.remove(me.getUser());
		return cc;
	}

	public boolean isAa() {
		return aa;
	}


	public void setAa(boolean aa) {
		this.aa = aa;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


	public String getTxt() {
		return txt;
	}


	public void setTxt(String txt) {
		this.txt = txt;
	}

}
