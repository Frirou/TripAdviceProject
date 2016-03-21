package com.esprit.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Checkin {
	
	private int id_checkIn;
	private Place place = new Place();
	private User user ;
	private float longitude;
	private float altitude;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId_checkIn() {
		return id_checkIn;
	}
	public void setId_checkIn(int id_checkIn) {
		this.id_checkIn = id_checkIn;
	}
	
	
	@ManyToOne
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getAltitude() {
		return altitude;
	}
	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	
}
