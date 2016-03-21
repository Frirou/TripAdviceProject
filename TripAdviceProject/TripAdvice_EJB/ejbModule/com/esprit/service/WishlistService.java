package com.esprit.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.persistence.Wishlist;

/**
 * Session Bean implementation class WishListService
 */

@Stateless(name=WishlistService.SERVICE_NAME)
public class WishlistService implements WishlistServiceRemote {


	@PersistenceContext(unitName="TripAdvice_EJB")
	EntityManager en;
    /**
     * Default constructor. 
     */
    public WishlistService() {
        
    }
    
    public List<Wishlist> findAllwish() {
		return en.createQuery("select p from Wishlist p").getResultList();
	}

	

	@Override
	public void addWishlist(Wishlist wishlist) {
				
				
		en.persist(wishlist);
			
		
	}

	@Override
	public void removewish(Wishlist wish) {
		en.remove(en.merge(wish));
		
	}

	@Override
	public List<Wishlist> listWishlisteByUser(int iduser) {

			Query q = en.createQuery("select a from Wishlist a");	
			List<Wishlist> mAll = new ArrayList<Wishlist>();
			List<Wishlist> mByid = new ArrayList<Wishlist>();
			mAll=q.getResultList();
			Iterator<Wishlist> it = mAll.iterator();

			while (it.hasNext()) {
				Wishlist m= new Wishlist();
				m=it.next();
				if(m.getPersonne().getId()==iduser){
					mByid.add(m);
					
				}

			 
			}
			 return mByid;
		}

	@Override
	public List<Wishlist> listWishlistByUserEvent(int iduser, int idplace) {
		Query q = en.createQuery("select a from Wishlist a");	
		List<Wishlist> mAll = new ArrayList<Wishlist>();
		List<Wishlist> mByid = new ArrayList<Wishlist>();
		mAll=q.getResultList();
		Iterator<Wishlist> it = mAll.iterator();

		while (it.hasNext()) {
			Wishlist m= new Wishlist();
			m=it.next();
			if((m.getPersonne().getId()==iduser) && (m.getPlace().getId_place()==idplace)){
				mByid.add(m);
				
			}

		 
		}
		 return mByid;
		

	}
		
		

	}

	
	

 

	

