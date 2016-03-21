package com.travel.web.mbeans;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;

import com.esprit.persistence.Place;
import com.esprit.service.PageServiceLocal;


@ManagedBean
@ApplicationScoped
public class HelperBean {

	@EJB
	private PageServiceLocal place;

	private byte[] defaultPicture;

	public HelperBean() {
	}

	@PostConstruct
	public void init() {

		InputStream is = FacesContext.getCurrentInstance()
										.getExternalContext()
											.getResourceAsStream("/resources/css/images/nopicture.jpg");

		try {
			defaultPicture = IOUtils.toByteArray(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Place findPlaceByName(String name) {
		return place.findPlaceByName(name);
	}
	


	public byte[] getDefaultPicture() {
		return defaultPicture;
	}

	public void setDefaultPicture(byte[] defaultPicture) {
		this.defaultPicture = defaultPicture;
	}

}
