package com.esprit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.persistence.Event;


/**
 * Session Bean implementation class EventServiceImpl
 */
@LocalBean
@Stateless
public class EventServiceImpl implements EventServiceRemote {

	@PersistenceContext(unitName="TripAdvice_EJB")
    EntityManager en;
	
    /**
     * Default constructor. 
     */
    public EventServiceImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addEvent(Event event) {
		en.persist(event);
		
	}

	@Override
	public void remove(Event event) {
		
		en.remove(en.merge(event));
		
	}

	@Override
	public void update(Event event) {
		en.merge(event);
		
	}

	@Override
	public Event getById(int i) {
		return en.find(Event.class, i);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> finAllEvent() {
		Query q = en.createQuery("select e from Event e ");		
		return q.getResultList();
		
	}

public List<Event> listEventByUser(int iduser) {
		
	
		
		Query q = en.createQuery("select a from Event a");	
		List<Event> mAll = new ArrayList<Event>();
		List<Event> mByid = new ArrayList<Event>();
		mAll=q.getResultList();
		Iterator<Event> it = mAll.iterator();

		while (it.hasNext()) {
			Event m= new Event();
			m=it.next();
			if(m.getPersonne().getId()==iduser){
				mByid.add(m);
				
			}

		 
		}
		 return mByid;

		
			
		
		
	}

	
	
}
