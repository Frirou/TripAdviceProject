package com.travel.web.mbeans;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.esprit.persistence.Place;
import com.esprit.persistence.Wishlist;
import com.esprit.service.WishlistServiceRemote;

@ManagedBean
@ViewScoped
public class wishBean {
	@EJB
private WishlistServiceRemote remote;
	
	private Wishlist  wish= new Wishlist();
	private List<Wishlist> wishlists;
	private List<Wishlist> fileteredwishlists;
	private boolean formDisplayed = false;
	private boolean Displayed = false;
	private Place selectedPlace;
	public wishBean() {
		// TODO Auto-generated constructor stub
	}
	public void dodeletewish(Wishlist wish) {

		remote.removewish(wish);
	
		
	}
	
	
	
	


	@PostConstruct
public void init(){
		
		wishlists = remote.findAllwish();
		System.out.println(wishlists.size()+"azeaz");
		
	}
	
	
	
	public List<Wishlist> getwishsbyId(int iduser) {

		wishlists = remote.listWishlisteByUser(iduser);
		return wishlists;
	}

	
	
	public void onRowSelect(SelectEvent event){
		setDisplayed(true);
		
	}
	
	
	
	
	
	
	public Wishlist getWish() {
		return wish;
	}
	public void setWish(Wishlist wish) {
		this.wish = wish;
	}
	public List<Wishlist> getWishlists() {
		return wishlists;
	}
	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}
	public List<Wishlist> getFileteredwishlists() {
		return fileteredwishlists;
	}
	public void setFileteredwishlists(List<Wishlist> fileteredwishlists) {
		this.fileteredwishlists = fileteredwishlists;
	}
	public boolean isFormDisplayed() {
		return formDisplayed;
	}
	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}
	public Place getSelectedPlace() {
		return selectedPlace;
	}
	public void setSelectedPlace(Place selectedPlace) {
		this.selectedPlace = selectedPlace;
	}
	public boolean isDisplayed() {
		return Displayed;
	}
	public void setDisplayed(boolean displayed) {
		Displayed = displayed;
	}

}
