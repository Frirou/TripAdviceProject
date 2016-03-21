package com.esprit.service;

import java.util.List;

import javax.ejb.Local;

import com.esprit.persistence.Ticket;



@Local
public interface TicketServiceLocal {
	
	 public void addTicket(Ticket ticket);
 	 public Ticket getById(int i);
 	public List<Ticket> listTicketByUserEvent(int iduser , int idevent);
	 
	
}
