package com.esprit.service;

import java.util.List;

import javax.ejb.Local;

import com.esprit.persistence.Comment;
import com.esprit.persistence.Place;


@Local

//Page Services
public interface PageServiceLocal {



	
	///Place Services
	
	void createPlace(Place place);
	void saveOrUpdatePlace(Place place);
	void removePlace(Place place);
	public byte[] findPictureByPlaceId(int idPlace);
	public Place findPlaceByName(String name);
	List<Place>findAllPlaces();
	public List<Place> listPlacesByUser(int iduser);

	
	
	//rate
	void ratePlace(Place p,int nbRate);
	
	
	
	//Comment
	void commentPlace(Comment c);
	List<Comment>findAllCommentsByPlace(int id);
	List<Comment>findAllComments();
    

	
	
}
