package com.esprit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.esprit.persistence.Comment;
import com.esprit.persistence.Place;



/**
 * Session Bean implementation class PageService
 */
@Stateless

public class PageService implements PageServiceLocal {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public PageService() {
        // TODO Auto-generated constructor stub
    }

   
    public List<Place> findAllPlaces() {
		return em.createQuery("select l from Place l").getResultList();
	}

	public void createPlace(Place place) {
	   em.persist(place);
		
	}

	@Override
	public void saveOrUpdatePlace(Place place) {
		em.merge(place);
		
	}

	@Override
	public void removePlace(Place place) {
		em.remove(em.merge(place));
		
	}
	
	public byte[] findPictureByPlaceId(int idPlace) {
		byte[] picture = null;
		Query query = em.createQuery("select p.img from Place p where p.id_place=:x");
		query.setParameter("x", idPlace);
		try{
			picture =  (byte[]) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no picture");
		}
		return picture;
		
	}
	@Override

public List<Place> listPlacesByUser(int iduser) {
		Query q = em.createQuery("select a from Place a");	
		List<Place> mAll = new ArrayList<Place>();
		List<Place> mByid = new ArrayList<Place>();
		mAll=q.getResultList();
		Iterator<Place> it = mAll.iterator();

		while (it.hasNext()) {
			Place m= new Place();
			m=it.next();
			if(m.getPersonne().getId()==iduser){
				mByid.add(m);
				
			}

		 
		}
		 return mByid;
		
	}
	
	
	
	

	public Place findPlaceByName(String name) {
		return (Place) em.createQuery("select l from Place l where l.nom=:c").setParameter("c",name).getSingleResult();

	}

	public void ratePlace(Place p,int rate) {
		int a=p.getNb_rate()+1;
		int b =p.getRate()+rate;
		
        p.setNb_rate(a);
		p.setRate(b);
		p.setMoyRate(b/a);
		em.merge(p);

	}

	public void commentPlace(Comment c) {
		em.persist(c);
	}

	public List<Comment> findAllCommentsByPlace(int id) 
	{
		
	return (List<Comment>) em.createQuery("select m from Comment m where m.place.id_place=:c").setParameter("c",id).getResultList();
		
	}


	public List<Comment> findAllComments() {
		return (List<Comment>) em.createQuery("select m from Comment m").getResultList();
	}



	

	

	

	
	
	
	
	

}


