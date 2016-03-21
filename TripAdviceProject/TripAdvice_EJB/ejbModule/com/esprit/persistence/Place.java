package com.esprit.persistence;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Place implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_place;
	private String nom;
	private String lieu;
	private String deecription;
    private byte[] img;
    private String video;
	private Page p ;


    
	
	private int nb_rate;
	private int rate ;
	private int moyRate;
	private int nbCheckIn;
	private float longitude;
	private float latitude;
	private Personne personne = new Personne();
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId_place() {
		return id_place;
	}
	public void setId_place(int id_place) {
		this.id_place = id_place;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getDeecription() {
		return deecription;
	}
	public void setDeecription(String deecription) {
		this.deecription = deecription;
	}
	
//	@Lob
//	@Basic(fetch=FetchType.LAZY)
//	public byte[] getImg() {
//		return img;
//	}
//	public void setImg(byte[] img) {
//		this.img = img;
//	}
	
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getNb_rate() {
		return nb_rate;
	}
	public void setNb_rate(int nb_rate) {
		this.nb_rate = nb_rate;
	}
	public int getNbCheckIn() {
		return nbCheckIn;
	}
	public void setNbCheckIn(int nbCheckIn) {
		this.nbCheckIn = nbCheckIn;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
	
	@Lob
    @Basic(fetch=FetchType.LAZY)
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public int getMoyRate() {
		return moyRate;
	}
	public void setMoyRate(int moyRate) {
		this.moyRate = moyRate;
	}
	@ManyToOne
	@JoinColumn(name="id_user")
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
	@ManyToOne

	public Page getP() {
		return p;
	}
	public void setP(Page p) {
		this.p = p;
	}
	
	
	
	
	

	
	
	
	
	
	
	

}
