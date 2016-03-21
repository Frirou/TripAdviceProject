package com.travel.web.mbeans;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;



import com.esprit.persistence.Place;
import com.esprit.service.PageServiceLocal;



@ManagedBean
@ViewScoped
public class PlaceBean {

	@EJB
	private PageServiceLocal pageServiceLocal;

	private Place place = new Place();
	private List<Place> places;
	private List<Place> fileteredPlaces;
	private boolean formDisplayed = false;
	
	public PlaceBean() {
		
	}
	
	@PostConstruct
	public void init(){
		
		places = pageServiceLocal.findAllPlaces();
		
	}
	public void doSaveOrUpdate(){
		
		AuthenticationBean me = (AuthenticationBean)
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
		place.setPersonne(me.getUser());
		pageServiceLocal.saveOrUpdatePlace(place);
		places = pageServiceLocal.findAllPlaces();
		formDisplayed =false;
		
	}
	  
	public List<Place> getPlacesbyId(int iduser) {

		places = pageServiceLocal.listPlacesByUser(iduser);
		return places;
	}
	    
	
	public void onStateChange(StateChangeEvent event) {  
        LatLngBounds bounds = event.getBounds();  
        int zoomLevel = event.getZoomLevel();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));  
    }  
      
    public void onPointSelect(PointSelectEvent event) {  
        LatLng latlng = event.getLatLng();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng())); 
        place.setLongitude((float) latlng.getLng());
        place.setLatitude((float) latlng.getLat());

        
    }  
      
    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
	
public void doNew(){
		
	place = new Place();
		formDisplayed = true;
		
	}


public void onRowSelect(SelectEvent event){
	formDisplayed= true;
	
}

public void doDelete(){
	
	
	pageServiceLocal.removePlace(place);
	places = pageServiceLocal.findAllPlaces();
	formDisplayed = false;
	
	}



public void doCancel(){
	
	
	place = new Place();
	places = pageServiceLocal.findAllPlaces();
	formDisplayed = false;
	
	
}
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Place> getFileteredPlaces() {
		return fileteredPlaces;
	}

	public void setFileteredPlaces(List<Place> fileteredPlaces) {
		this.fileteredPlaces = fileteredPlaces;
	}
	
	
	public List<Place> findPlacesByUser(int id){

        places=pageServiceLocal.listPlacesByUser(id);
        return places;
        


	}


	public void handleFileUpload(FileUploadEvent event) {
		place.setImg(event.getFile().getContents());

	}

}
