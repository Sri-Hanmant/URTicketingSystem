package com.ur.jsfactivation;

import javax.inject.Inject;

public class UserHelper {
	
	 @Inject
	 private userBean login;
	
	 
	 public String email() {
		 return login.getUsEntity().getUserEmail();
	 }

}
