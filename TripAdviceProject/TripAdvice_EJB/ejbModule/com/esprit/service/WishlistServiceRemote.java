package com.esprit.service;

import java.util.List;

import javax.ejb.Local;

import com.esprit.persistence.Wishlist;
@Local
public interface WishlistServiceRemote {


	public final String SERVICE_NAME="WishlistService";
	 public void  addWishlist(Wishlist wishlist);
	 public List<Wishlist> findAllwish();
	
	 void removewish(Wishlist wish);
	 public List<Wishlist> listWishlisteByUser(int iduser);
	 	  
	 public List<Wishlist> listWishlistByUserEvent(int iduser, int idplace);
	
}
