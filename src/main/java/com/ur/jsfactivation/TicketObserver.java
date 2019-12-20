package com.ur.jsfactivation;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ticket.dbconnection.hibernateConnection;
import com.ur.entities.Observer;
import com.ur.entities.Subject;

public class TicketObserver extends Observer{
	private String tID = " ";
	private String status = " ";
	hibernateConnection conn = new hibernateConnection();
	
	public TicketObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	public String gettIDParam(FacesContext fc) {
		 Map<String,String> params=fc.getExternalContext().getRequestParameterMap();
		 return params.get("tID");
	}
	
	@Override
	public void update() {
		FacesContext fc = FacesContext.getCurrentInstance();
		this.tID = gettIDParam(fc);
		Session session = hibernateConnection.getSession();
		Transaction tran = conn.getTransaction(session);
		status = (String) session.createSQLQuery("SELECT priority FROM ticket_details WHERE ticketId='" + tID + "'").uniqueResult();
		conn.closeSessionwithCommit(session);
		System.out.println(status);
	}
}