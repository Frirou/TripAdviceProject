package com.travel.web.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.context.RequestContext;

import com.esprit.persistence.Comment;
import com.esprit.persistence.Place;
import com.esprit.persistence.Wishlist;
import com.esprit.service.PageServiceLocal;
import com.esprit.service.WishlistServiceRemote;


@ManagedBean(name="tableBean")
@ViewScoped

public class TableBean{
	


	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private WishlistServiceRemote whishRemote;
	private Wishlist wish = new Wishlist();
	private Place place = new Place();
	private Comment comment= new Comment();
	private List<Place> places;
	private List<Comment> comments;

	private Place selectedPlace;
	AuthenticationBean me = (AuthenticationBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
//	private MapModel advancedModel;  


	public TableBean() {

		
	  



	}
	
//	public MapModel getAdvancedModel() { 
//		  advancedModel = new DefaultMapModel();  
//		    LatLng coord1 = new LatLng(selectedPlace.getLatitude(),selectedPlace.getLongitude());
//		    advancedModel.addOverlay(new Marker(coord1, "Konyaalti"));
//	    return advancedModel;  
//	} 
	@PostConstruct
	public void init(){
		

		places = pageServiceLocal.findAllPlaces();
		comments= new ArrayList<Comment>();

//		comments=pageServiceLocal.findAllComments();

	}
	
	public List<Comment> findComm(int id){

        comments=pageServiceLocal.findAllCommentsByPlace(id);
return comments;
		
	}
	
	
	
	
	public void doNewComment(){
		comment.setNb_reported(0);
		comment.setPersonne(me.getUser());
		comment.setPlace(selectedPlace);
		
		pageServiceLocal.commentPlace(comment);
		comment.setText("");
				
	}
	
	
public String isrendred3() {
		
		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");
		wish.setPersonne(me.getUser());
		wish.setPlace(selectedPlace);
	
		if( whishRemote.listWishlistByUserEvent(wish.getPersonne().getId(), wish.getPlace().getId_place()).size() == 0)
		{
			return "aa";
		}
		else {return "Place added to wishlist with success";}		
	}
	public void doAddWish(){
		

		
	
		
		//PlaceBean me = (PlaceBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("placeBean");
		AuthenticationBean me = (AuthenticationBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
		
		wish.setPersonne(me.getUser());
		wish.setPlace(selectedPlace);
		whishRemote.addWishlist(wish);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "wish", isrendred3());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
      
	
	      
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

	public Place getSelectedPlace() {
		return selectedPlace;
	}

	public void setSelectedPlace(Place selectedPlace) {
		this.selectedPlace = selectedPlace;
	}

	
	public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
 
        FacesContext.getCurrentInstance().addMessage(null, message);
        int rate=(Integer) rateEvent.getRating();
        
        pageServiceLocal.ratePlace(selectedPlace,rate );
        
    }
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	

    public void handleToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fieldset Toggled", "Visibility:" + event.getVisibility());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
	
	
	public int showRate(){
		int a =selectedPlace.getRate();
		int b=selectedPlace.getNb_rate();
	return	a/b;
		
	}

	public Wishlist getWish() {
		return wish;
	}

	public void setWish(Wishlist wish) {
		this.wish = wish;
	}
	
	
		


	
	

	
	
}
		