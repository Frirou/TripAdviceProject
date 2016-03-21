package com.esprit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.persistence.Event;

/**
 * Session Bean implementation class EventServiceImpl
 */
@Stateless
public class EventService implements EventServiceLocal {

	@PersistenceContext
	EntityManager en;

	/**
	 * Default constructor.
	 */
	public EventService() {
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

	@Override
	public List<Event> listEventByUser(int iduser) {

		Query q = en.createQuery("select a from Event a");
		List<Event> mAll = new ArrayList<Event>();
		List<Event> mByid = new ArrayList<Event>();
		mAll = q.getResultList();
		Iterator<Event> it = mAll.iterator();

		while (it.hasNext()) {
			Event m = new Event();
			m = it.next();
			if (m.getPersonne().getId() == iduser) {
				mByid.add(m);

			}

		}
		return mByid;

	}

	@Override
	public void saveOrUpdateEvent(Event event) {
		en.merge(event);

	}

	@Override
	public List<Event> listEventNotMe(int iduser) {
		Query q = en.createQuery("select a from Event a");
		List<Event> mAll = new ArrayList<Event>();
		List<Event> mByid = new ArrayList<Event>();
		mAll = q.getResultList();
		Iterator<Event> it = mAll.iterator();

		while (it.hasNext()) {
			Event m = new Event();
			m = it.next();
			if (m.getPersonne().getId() != iduser) {
				mByid.add(m);

			}

		}
		return mByid;
	}

	public byte[] findPictureByPlaceId(int idEvent) {
		byte[] picture = null;
		Query query = en.createQuery("select p.img from Event p where p.idEvent=:x");
		query.setParameter("x", idEvent);
		try {
			picture = (byte[]) query.getSingleResult();
		} catch (Exception ex) {
			
		}
		return picture;
	}

}
