package com.esprit.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Message implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String text;
	
	private Personne pSend = new Personne();
	private Personne pReceive = new Personne();
	private Date date;

	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@ManyToOne
	@JoinColumn(name="Id_sender")
	public Personne getpSend() {
		return pSend;
	}
	public void setpSend(Personne pSend) {
		this.pSend = pSend;
	}
	@ManyToOne
	@JoinColumn(name="Id_Receiver")
	public Personne getpReceive() {
		return pReceive;
	}
	public void setpReceive(Personne pReceive) {
		this.pReceive = pReceive;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
