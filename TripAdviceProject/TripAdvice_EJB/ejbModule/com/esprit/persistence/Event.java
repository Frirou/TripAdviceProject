package com.esprit.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity

public class Event implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEvent;
	private String name;
	private String address;
	private int nbrTicket;
	private Date end_date;
	private Date begin_date;
	private Personne personne;
//	private List<Personne> participants;
	private String typeevt ;
	private boolean blocked;
	
	private byte[] img;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNbrTicket() {
		return nbrTicket;
	}
	public void setNbrTicket(int nbrTicket) {
		this.nbrTicket = nbrTicket;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	
	@ManyToOne
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	
//	@OneToMany
//	public List<Personne> getParticipants() {
//		return participants;
//	}
//	public void setParticipants(List<Personne> participants) {
//		this.participants = participants;
//	}
	public String getTypeevt() {
		return typeevt;
	}
	public void setTypeevt(String typeevt) {
		this.typeevt = typeevt;
	}
	
	@Lob
    @Basic(fetch=FetchType.LAZY)
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	


}
