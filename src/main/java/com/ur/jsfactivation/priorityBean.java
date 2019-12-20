package com.ur.jsfactivation;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.Query;
import com.ticket.dbconnection.hibernateConnection;
import com.ur.entities.priority;
import com.ur.entities.ticket;

@Named
@RequestScoped
public class priorityBean {
	private List<ticket> ticketList = new ArrayList();
	private int currentCount = 0;
	private String tID = " ";
	private int intTID = 0;
	private String user = " "; //USER WILL HAVE TO BE TAKEN FROM SESSION VARIABLE WHAT ITS MADE
	private String dbUser = " ";
	private priority p = new priority();
	private Boolean comp = true;
    hibernateConnection conn = new hibernateConnection();
	
    public priority getP() {
		return p;
	}
    
	public void setP(priority p) {
		this.p = p;
	}	

	public String gettIDParam(FacesContext fc) {
		 Map<String,String> params=fc.getExternalContext().getRequestParameterMap();
		 return params.get("tID");
	}
	
	public static boolean compare(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equals(str2));
	}
	
	public void priorityUpdate(int curCount, String tic) {
		if(curCount > 9) {
			Session session1 = hibernateConnection.getSession();
			Transaction tran1 = conn.getTransaction(session1);
			org.hibernate.query.Query query1 = session1.createQuery("UPDATE ticket SET priority = 'High' WHERE ticketID = '" + tID + "'");
			int result1 = query1.executeUpdate();
			conn.closeSessionwithCommit(session1);
		}
		else if(curCount > 4) {
			Session session2 = hibernateConnection.getSession();
			Transaction tran2 = conn.getTransaction(session2);
			org.hibernate.query.Query query2 = session2.createQuery("UPDATE ticket SET priority = 'Medium' WHERE ticketID = '" + tID + "'");
			int result2 = query2.executeUpdate();
			conn.closeSessionwithCommit(session2);
		}
	}
	
	public String sendPDetailstodb() throws UnsupportedEncodingException, MessagingException {
		FacesContext fc = FacesContext.getCurrentInstance();
		this.tID = gettIDParam(fc);
		intTID = Integer.parseInt(tID);
		Session session = hibernateConnection.getSession();
		Transaction tran = conn.getTransaction(session);
		dbUser = (String) session.createSQLQuery("SELECT userId FROM priority_details WHERE ticketId='" + tID + "' AND userId='" + user + "'").uniqueResult();
		comp = compare(user, dbUser);
		if(comp == true) {
			conn.closeSessionwithCommit(session);
		}
		else {
			p.setUserId(user);
			p.setTicketId(intTID);
			currentCount = (Integer) session.createSQLQuery("SELECT priorityCount FROM ticket_details WHERE ticketID='" + tID + "'").uniqueResult();
			currentCount++;
			org.hibernate.query.Query query = session.createQuery("UPDATE ticket SET priorityCount = '" + currentCount + "' WHERE ticketID = '" + tID + "'");
			int result = query.executeUpdate();
			priorityUpdate(currentCount, tID);
			session.save(p);
			conn.closeSessionwithCommit(session);
		}
		return "done";
	}
}
