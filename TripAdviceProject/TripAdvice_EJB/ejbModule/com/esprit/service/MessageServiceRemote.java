package com.esprit.service;

import java.util.List;

import javax.ejb.Local;

import com.esprit.persistence.Message;
import com.esprit.persistence.User;

@Local
public interface MessageServiceRemote{
  public void addMessage(Message m);
	public List<Message> finAllMessage();
	public List<Message> listMessageByUser(User user);
	public List<Message> getMessageByClents(int id1, int id2);	

	

}