package com.travel.web.mbeans;

import java.util.Date;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
@ManagedBean(name="mailingBean")
@ViewScoped


public class Mail {
	private String to;
	private String subject;
	private String corp;
	private Date date;

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Mail() {
	}

	public Mail(String to, String subject, String corp, Date date) {
		this.to = to;
		this.subject = subject;
		this.corp = corp;
		this.date = date;
	}
	
	
	public String send()
	{
		try
		{
			String username = "stardev.travel@gmail.com";
			String password = "espritesprit";
		    
			String recipient = to ;
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.from", "stardev.travel@gmail.com");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			props.setProperty("mail.debug", "true");
			Session session = Session.getInstance(props, null);
			MimeMessage msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO, recipient);
						msg.setSubject("Event");
			msg.setSentDate(new Date());
			msg.setText(corp);
			Transport transport = session.getTransport("smtp");
			transport.connect(username, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("mail envoyé");
		}
		catch(MessagingException me)
		{
				me.printStackTrace();
		}
		
		return null;
	}

}
