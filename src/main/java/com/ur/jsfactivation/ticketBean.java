package com.ur.jsfactivation;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.ticket.dbconnection.hibernateConnection;
import com.ur.entities.ticket;

@Named
@RequestScoped
public class ticketBean {
	@Inject
	private userBean user;

	private ticket tk = new ticket();
	private ticket editTicket = new ticket();
	hibernateConnection conn = new hibernateConnection();
    Date date = new Date();
    TicketObservable tktObs=new TicketObservable();
	NotifyObservers subscriber=new NotifyObservers();
    
    public ticket getTk() {
		return tk;
	}

	public void setTk(ticket tk) {
		this.tk = tk;
	}

	public ticket getEditTicket() {
		return editTicket;
	}

	public void setEditTicket(ticket editTicket) {
		this.editTicket = editTicket;
	}

	public String sendTkDetailstodb() {
		tk.setUser(user.getUsEntity().getUserEmail());
		tk.setPriority("Low");
		tk.setWorker("Sri");
		tk.setDepartment("Maintenance");
		tk.setStatus("Open");
		tk.setDateCreated(date.toString());
		tk.setLastMod(date.toString());
	  try {
		    Session session = hibernateConnection.getSession();
			Transaction tran = conn.getTransaction(session);
			session.save(tk);
			conn.closeSessionwithCommit(session);
			
			tktObs.addObserver(subscriber);
			tktObs.setTicket(tk,true);
			
	         String message = "Ticket has been created successfully";
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));
			 this.clear();
			return "null";
			
			
	  }catch(Exception e) {
		    String message = e.getMessage();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			this.clear();
			return null;
		 }
		
	}

	public String editTicketDetails(int ticketId) {
		if (!user.getUsEntity().getIsAdmin()) {
			return null;
		}
		Session session = hibernateConnection.getSession();
		Transaction tran = conn.getTransaction(session);
		Query query = session.createQuery("FROM ticket where ticketId =:ticketId", ticket.class);
		query.setParameter("ticketId", ticketId);
		List<ticket> tklist = query.list();
		conn.closeSessionwithCommit(session);
        
		if (tklist.size() > 0) {
			this.setEditTicket(tklist.get(0));
			return "success";
		} else {
			return "faiure";
		}

	}
	
	public String editUserTicketDetails(int ticketId) {
		Session session = hibernateConnection.getSession();
		Transaction tran = conn.getTransaction(session);
		Query query = session.createQuery("FROM ticket where ticketId =:ticketId", ticket.class);
		query.setParameter("ticketId", ticketId);
		List<ticket> tklist = query.list();
		conn.closeSessionwithCommit(session);
      
		if (tklist.size() > 0) {
			this.setEditTicket(tklist.get(0));
			return "success";
		} else {
			return "faiure";
		}

	}


	public String updateTkDetailstodb(int ticketId) {
        
		this.getEditTicket().setLastMod(date.toString());
		Session session = hibernateConnection.getSession();
		Transaction tran = conn.getTransaction(session);
		session.update(this.getEditTicket());
		conn.closeSessionwithCommit(session);
		
         String message = "Ticket has been Updated successfully";
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));
		 tktObs.addObserver(subscriber);
		 tktObs.setTicket(this.getEditTicket(),false);
		 return "success";

	}
	
	
	public List<ticket> getMyTickets(){
		ticketRepository repo = new ticketRepository();
		String email=user.getUsEntity().getUserEmail();
		return repo.getTickets(email);
	}
	
	public void clear(){
		tk=new ticket();
	}

}
