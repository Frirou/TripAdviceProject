package com.esprit.service;

import java.util.List;

import javax.ejb.Local;

import com.esprit.persistence.Event;


@Local
public interface EventServiceRemote {
	
    public void addEvent(Event event);
	public  void remove(Event event);
	public void update(Event event) ;
	public Event getById(int i);
	public List<Event> finAllEvent();
	public List<Event> listEventByUser(int iduser);
 
}
