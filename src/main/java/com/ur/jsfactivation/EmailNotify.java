//EmailNotify class that sends the email notifications
package com.ur.jsfactivation;


import java.util.List;



import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ticket.dbconnection.hibernateConnection;
import com.ur.entities.ticket;
import com.ur.entities.user;

public class EmailNotify {

	
	 private static final String HOST = "smtp.gmail.com";
	    private static final int PORT = 465;
	    private static final boolean SSL_FLAG = true; 
	    private String userName = "cs476finalproject@gmail.com";
        private String password = "Project476!";
        private String fromAddress="cs476finalproject@gmail.com";
	  
	    public void sendSimpleEmail(ticket tkt,boolean isCreate) {
	      if(tkt.isNotify()) {
	    	  this.sendEmail(tkt,tkt.getUser(),isCreate);
	      }
	      
	      List<user> admins = this.adminUsers();
	      
	      for(int i=0;i < admins.size();i++) {
	    	  this.sendEmail(tkt,admins.get(i).getUserEmail(),isCreate);
	      }
	    	
	    }
	    
	    private List<user> adminUsers() {
	    	hibernateConnection conn = new hibernateConnection();
	    	Session session = hibernateConnection.getSession();
			Transaction tran = conn.getTransaction(session);
			Query query = session.createQuery("from user where isAdmin = :isAdmin",user.class);
			query.setParameter("isAdmin",true);
			List<user> uslist=query.list();
			return uslist;
	    }
	    
	    private void sendEmail(ticket tkt,String toAddress,boolean isCreate) {
	    	   
		       
		        String subject = "Ticket Id : "+ tkt.getTicketId() + " Title : "+tkt.getTitle();
		        StringBuffer message = new StringBuffer();
		        if(isCreate) {
		        	 message.append("A Ticket has been created");
		        }else {
		        	 message.append("Ticket has been updated");
		        }
		       
		        message.append(System.lineSeparator());
		        message.append("Ticket Id : "+tkt.getTicketId());
		        message.append(System.lineSeparator());
		        message.append("Title : "+tkt.getTitle());
		        message.append(System.lineSeparator());
		        message.append("Created By : "+tkt.getUser());
		        message.append(System.lineSeparator());
		        message.append("Comments : "+tkt.getComments());
		       
		         
		        try {
		            Email email = new SimpleEmail();
		            email.setHostName(HOST);
		            email.setSmtpPort(PORT);
		            email.setAuthenticator(new DefaultAuthenticator(userName, password));
		            email.setSSLOnConnect(SSL_FLAG);
		            email.setFrom(fromAddress);
		            email.setSubject(subject);
		            email.setMsg(message.toString());
		            email.addTo(toAddress);
		            email.send();
		        }catch(Exception ex){
		            System.out.println("Unable to send email");
		            System.out.println(ex);
		        }
	    	
	    }
	    	    
}
