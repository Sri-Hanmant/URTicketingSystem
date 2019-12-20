//displayTicket class that displays all the tickets
package com.ur.jsfactivation;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ticket.dbconnection.hibernateConnection;
import com.ur.entities.ticket;

@Named
@RequestScoped
public class displayTicketsList {
	private List<ticket> ticketList = new ArrayList();
	hibernateConnection conn = new hibernateConnection();
	
	public List<ticket> getTickets(){
		
		Session session = hibernateConnection.getSession();
		Transaction tran = conn.getTransaction(session);
        ticketList = session.createQuery("FROM ticket").list(); 
        conn.closeSessionwithCommit(session);

		return ticketList;	
	
	}
	
}