package com.travel.web.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.esprit.persistence.Place;
import com.esprit.service.PageServiceLocal;

@ApplicationScoped
@ManagedBean
public class MapBean implements Serializable {

	/**
	 * 
	 */

	
	@EJB
	PageServiceLocal travelPlaceServiceRemote;
	private static final long serialVersionUID = 1L;
	private Place travelPlace;
	private MapModel simpleModel;
	private MapModel emptyModel;
	private String title;
	private double lat;
	private double lng;
	private int x;
	public static float la;
	public static float ln;
	private List<Place> travelPlaces;
	private Marker marker;
	
	StreamedContent image;

	public MapBean() {
		emptyModel = new DefaultMapModel();

	}

	@PostConstruct
	public void init() {
		simpleModel = new DefaultMapModel();

		
		
		
	}
	public DefaultMapModel  aff(){
		travelPlaces = travelPlaceServiceRemote.findAllPlaces();
		x=0;
		for (Place t : travelPlaces) {
			LatLng coord1 = new LatLng(t.getLatitude(), t.getLongitude());
			// Basic marker
			simpleModel.addOverlay(new Marker(coord1, t.getNom(),t.getLieu()));

			
			travelPlace = t;

		}
		return (DefaultMapModel) simpleModel;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public Marker getMarker() {
		return marker;
	}

	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public void addMarker(ActionEvent actionEvent) {
		Marker marker = new Marker(new LatLng(lat, lng), title);
		emptyModel.addOverlay(marker);
		la = (float) lat;
		ln = (float) lng;
		FacesMessage msg = new FacesMessage("Marker Added", "Lat:" + lat
				+ ", Lng:" + lng);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public float getLa() {
		return la;
	}

	public float getLn() {
		return ln;
	}

	public MapModel getSimpleModel() {
		return aff();
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public List<Place> getTravelPlaces() {
		return travelPlaces;
	}

	public void setTravelPlaces(List<Place> travelPlaces) {
		this.travelPlaces = travelPlaces;
	}

	public Place getTravelPlace() {
		return travelPlace;
	}

	public void setTravelPlace(Place travelPlace) {
		this.travelPlace = travelPlace;
	}



	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

}