package com.ur.jsfactivation;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class loginFilter implements Filter {

    @Inject
	private userBean loginBean;
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String loginURL = request.getContextPath() + "/loginPage.xhtml";
	    boolean loggedIn = (loginBean != null) && (loginBean.isLoggedIn());
		if (!loggedIn) {
				response.sendRedirect(loginURL); 
			}else
			   chain.doFilter(req, res);
         }

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}

}
