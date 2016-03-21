package com.esprit.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.persistence.Ticket;



/**
 * Session Bean implementation class EventServiceImpl
 */
@Stateless
public class TicketService implements TicketServiceLocal {

	@PersistenceContext
    EntityManager en;
	
    /**
     * Default constructor. 
     */
    public TicketService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addTicket(Ticket ticket) {
				
		en.persist(ticket);
		
	}

	
	@Override
	public Ticket getById(int i) {
		return en.find(Ticket.class, i);		
	}

public List<Ticket> listTicketByUserEvent(int iduser , int idevent) {
		
	
		
		Query q = en.createQuery("select a from Ticket a");	
		List<Ticket> mAll = new ArrayList<Ticket>();
		List<Ticket> mByid = new ArrayList<Ticket>();
		mAll=q.getResultList();
		Iterator<Ticket> it = mAll.iterator();

		while (it.hasNext()) {
			Ticket m= new Ticket();
			m=it.next();
			if((m.getPersonne().getId()==iduser) && (m.getEvent().getIdEvent()==idevent)){
				mByid.add(m);
				
			}

		 
		}
		 return mByid;
		
	}
	
}
