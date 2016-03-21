package com.esprit.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Circle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String type;
	
	private Personne pcreate = new Personne();
	private Personne pParticipate = new Personne();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="Id_create")
	public Personne getPcreate() {
		return pcreate;
	}

	public void setPcreate(Personne pcreate) {
		this.pcreate = pcreate;
	}

	@ManyToOne
	@JoinColumn(name="Id_participate")
	public Personne getpParticipate() {
		return pParticipate;
	}

	public void setpParticipate(Personne pParticipate) {
		this.pParticipate = pParticipate;
	}
	
	
	

	
}
