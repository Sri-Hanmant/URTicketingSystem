package com.ur.jsfactivation;

import java.util.Observable;

import com.ur.entities.ticket;

public class TicketObservable extends Observable {

	private ticket tk;
	private Boolean isCreate;

	public ticket getTicket() {
		return tk;
	}
	
	public boolean getIsCreate() {
		return isCreate;
	}

	public void setTicket(ticket tk,Boolean isCreate) {
		this.tk = tk;
		this.isCreate=isCreate;
		setChanged();
		notifyObservers();
	}

}
