package com.security.project.web.service.provider.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.security.project.web.service.UserContext;
import com.security.project.web.service.domain.CustomUser;


/*
 * This class is for getting and adding users to the security context. This should provide for using our user objects within jsp's,
 * however, more testing needs to be done to ensure that it is in fact being done.
 * 
 * unresolved questions: 
 * 						1: Does UserAuthenticationManager need to explicitly make the call to setCurrentUser, 
 * 							or does SpringSecurity do it automagically?
 *  
 */
public class SpringSecurityUserContext implements UserContext{

	private final UserDetailsService uds;

	@Autowired
	public SpringSecurityUserContext(UserDetailsService uds) {
		this.uds = uds;
	}
	  
	public CustomUser getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext(); 
	    Authentication authentication = context.getAuthentication();
	    if (authentication == null) {
	      return null;
	    }
//	    String email = authentication.getName();
	    return (CustomUser) authentication.getPrincipal();
	}

	public void setCurrentUser(CustomUser user) {
		UserDetails userDetails = uds.loadUserByUsername(user.getEmail()); 
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities()); 
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
