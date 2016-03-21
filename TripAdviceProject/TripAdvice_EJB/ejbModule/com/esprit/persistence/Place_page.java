package com.esprit.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Place_page implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_place_page;
	private Place place =new Place();
	private Page page =new Page();
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId_place_page() {
		return id_place_page;
	}

	public void setId_place_page(int id_place_page) {
		this.id_place_page = id_place_page;
	}
	
	@ManyToOne
	@JoinColumn(name="id_place")
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	@ManyToOne
	@JoinColumn(name="id_page")
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	
	
	
	
	

}
