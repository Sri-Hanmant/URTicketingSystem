package com.ur.jsfactivation;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ticket.dbconnection.hibernateConnection;
import com.ur.entities.user;

@Named
@SessionScoped
public class userBean implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7668652809309537299L;
	/**
	 * 
	 */
	
	private user userDetails = new user();
    hibernateConnection conn = new hibernateConnection();
    private user usEntity=new user();
    Boolean isLoggedIn =false;
	
    public Boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public user getUsEntity() {
		return usEntity;
	}
	public void setUsEntity(user usEntity) {
		this.usEntity = usEntity;
	}
	public user getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(user userDetails) {
		this.userDetails = userDetails;
	}
	
	
	
	public String login() {
		hibernateConnection.setSession();
		Session session = hibernateConnection.getSession();
		Transaction tran = conn.getTransaction(session);
		Query query = session.createQuery("from user where userEmail = :userEmail and password = :password",user.class);
		query.setParameter("userEmail", getUserDetails().getUserEmail());
		query.setParameter("password", getUserDetails().getPassword());
		
		List<user> uslist=query.list();
		conn.closeSessionwithCommit(session);
		if(uslist.size()>0) {
			this.setUsEntity(uslist.get(0));
			this.setIsLoggedIn(true);
			return "success";
		}else {
			String message = "Email or Password does not exist";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message,null));
			return null;
		}
			
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/loginPage.xhtml?faces-redirect=true";
	}
	
}
