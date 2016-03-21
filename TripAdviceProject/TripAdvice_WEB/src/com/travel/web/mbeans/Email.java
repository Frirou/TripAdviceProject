package com.travel.web.mbeans;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.esprit.persistence.User;

public class Email {
	private String urlConfirmation = "http://localhost:8181/TripAdvice_WEB/confirmation.jsf?id=";
	private String mail;
	private User user;
	
	
	public void sendConfirmationMail(User user) {
		
		System.out.println("user id : "+user.getId());
		// Sender's email ID needs to be mentioned
		String from = "emna.gharbi@esprit.tn";

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "465");
		Authenticator authenticator = new Authenticator() {
				private PasswordAuthentication authentication;
					{
						authentication = new PasswordAuthentication(
								"stardev.travel@gmail.com", "espritesprit");
					}

					protected PasswordAuthentication getPasswordAuthentication() {
						return authentication;
					}
				};
				// Get the default Session object.
				Session session = Session.getInstance(properties, authenticator);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					user.getMail()));

			// Set Subject: header field
			message.setSubject("Confirmation du compte");

			// Now set the actual message
			message.setText("Bonjour <strong>"+user.getPrenom()+" "+user.getNom()+"</strong> <br /><br />"
					+ "Merci d'avoir choisi Trip Advice "
					+ "Pour commencer à utiliser Trip Advice veuillez cliquer <a href=\""+ urlConfirmation + user.getId() + "\"> ici </a>"
					+"<br /><br /> Cordialement", "UTF-8", "html");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}
