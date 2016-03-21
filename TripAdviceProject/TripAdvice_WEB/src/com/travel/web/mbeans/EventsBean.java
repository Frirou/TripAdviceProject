package com.travel.web.mbeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import com.esprit.persistence.Event;
import com.esprit.persistence.Ticket;
import com.esprit.service.EventServiceLocal;
import com.esprit.service.TicketServiceLocal;

@ManagedBean
@ViewScoped
public class EventsBean {

	@EJB
	private EventServiceLocal eventServiceLocal;
	private Event event1 = new Event();

	@EJB
	private TicketServiceLocal ticketLocal;
	private Ticket ticket = new Ticket();

	private List<Event> events;
	private List<Event> fileteredPlaces;
	private boolean formDisplayed = false;
	private Mail mail = new Mail();
	private Date date = new Date();
	private Date date2 = new Date();
	private boolean disable = false;
	private boolean disable2 = true;
	
	private boolean block ;

	private String radio;
	private String input1;
	private String input2;

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public EventsBean() {

	}

	@PostConstruct
	public void init() {

		events = eventServiceLocal.finAllEvent();

	}
	
	public void handleFileUpload(FileUploadEvent event) {
				
		event1.setImg(event.getFile().getContents());

	}

	public void doSaveOrUpdate() {

		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");
		event1.setPersonne(me.getUser());
		if ((event1.getBegin_date().before(event1.getEnd_date()))) {
			
			event1.setBlocked(block);
			event1.setTypeevt(radio);
			eventServiceLocal.saveOrUpdateEvent(event1);
			events = eventServiceLocal.finAllEvent();
			formDisplayed = false;
		}

		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Alert ", "Verify the Date.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public Event getEvent() {
		return event1;
	}

	public void setEvent(Event event) {
		this.event1 = event;
	}

	public void doNew() {

		event1 = new Event();
		formDisplayed = true;

	}

	public void onRowSelect(SelectEvent event) {
		formDisplayed = true;

		if (isrendred3()) {
			setDisable(true);
			setDisable2(false);
		}

		else {
			setDisable(false);
			setDisable2(true);
		}

	}

	public void doDelete() {

		eventServiceLocal.remove(event1);
		events = eventServiceLocal.finAllEvent();
		formDisplayed = false;

	}

	public void doCancel() {

		event1 = new Event();
		events = eventServiceLocal.finAllEvent();
		formDisplayed = false;

	}

	public void reserver() {

		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		int i = event1.getNbrTicket();

		if ((i > 0) && (event1.getTypeevt().equals("payant") ) && (event1.isBlocked()==false)    ) {

			event1.setNbrTicket(i - 1);
			eventServiceLocal.saveOrUpdateEvent(event1);
			events = eventServiceLocal.finAllEvent();
			formDisplayed = false;

			mail.setCorp("you are subscrib in event   " + event1.getName()
					+ "  in   " + event1.getAddress());
			mail.setTo(me.getUser().getMail());
			mail.send();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Confirmation mail", "Participation done with success.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);

			ticket.setEvent(event1);
			ticket.setPersonne(me.getUser());
			ticketLocal.addTicket(ticket);

		}

		else if ((i == 0) && (event1.getTypeevt().equals("gratuit"))) {

			eventServiceLocal.saveOrUpdateEvent(event1);
			events = eventServiceLocal.finAllEvent();
			formDisplayed = false;

			mail.setCorp("you are subscrib in event   " + event1.getName()
					+ "in   " + event1.getAddress());
			mail.setTo(me.getUser().getMail());
			mail.send();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Confirmation Msg", "Participation done with success.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);

			ticket.setEvent(event1);
			ticket.setPersonne(me.getUser());
			ticketLocal.addTicket(ticket);
		}

		else {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Confirmation Msg", "Participation denied.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public void Annuler() {

		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		int i = event1.getNbrTicket();
		if ((event1.getTypeevt().equals("payant"))) {
			event1.setNbrTicket(i + 1);
			eventServiceLocal.saveOrUpdateEvent(event1);
			events = eventServiceLocal.finAllEvent();
			formDisplayed = false;

			mail.setCorp("you are annuler your subscrib in event   "
					+ event1.getName() + "  in   " + event1.getAddress());
			mail.setTo(me.getUser().getMail());
			mail.send();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Confirmation mail", "Cancel  done with success.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);

		}

	}

	public Boolean isrendred() {

		int i = event1.getNbrTicket();
		return (i > 0) && (event1.getTypeevt().equals("payant"));
	}

	public Boolean isrendred1() {
		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		return eventServiceLocal.listEventByUser(me.getUser().getId()).size() != 0;
	}

	public Boolean isrendred2() {
		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		return ticketLocal.listTicketByUserEvent(me.getUser().getId(),
				event1.getIdEvent()).size() != 0;
	}

	public Boolean isrendred3() {
		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		return ticketLocal.listTicketByUserEvent(me.getUser().getId(),
				event1.getIdEvent()).size() == 0;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Event> getFileteredPlaces() {
		return fileteredPlaces;
	}

	public void setFileteredPlaces(List<Event> fileteredPlaces) {
		this.fileteredPlaces = fileteredPlaces;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Event> getEventsbyId(int iduser) {

		events = eventServiceLocal.listEventByUser(iduser);
		return events;
	}

	public List<Event> getEventsNotMe(int iduser) {

		events = eventServiceLocal.listEventNotMe(iduser);
		return events;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public boolean isDisable2() {
		return disable2;
	}

	public void setDisable2(boolean disable2) {
		this.disable2 = disable2;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

}
