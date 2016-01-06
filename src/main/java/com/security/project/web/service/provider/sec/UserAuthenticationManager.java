package com.security.project.web.service.provider.sec;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.project.web.service.UserService;
import com.security.project.web.service.domain.CustomUser;

/*
 * Things to do: 
 * 				1. 2-factor Auth
 * 				2. Hash PW
 * 				3. 
 */

public class UserAuthenticationManager implements AuthenticationProvider{

	private UserService userService;
	
	@Autowired
	public UserAuthenticationManager(UserService userDao){
		this.userService = userDao;
	}
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication; 
		
	    String password = "";
	    
		String email = token.getName(); 
		String pw = null;
		
		CustomUser user = userService.findUserByEmail(email);
		
	    if(email != null) {
	    	password = user.getPassword();
	    } 
	    if(password == null) { 
	      throw new UsernameNotFoundException("Invalid username/password"); 
	    } 
	    
	    if(!password.equals(token.getCredentials())) { 
	      throw new BadCredentialsException("Invalid username/password"); 
	    } 
	    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	    		
	    return new UsernamePasswordAuthenticationToken(email, password, authorities); 
	  } 


	public boolean supports(Class<?> authentication) { 
	    return UsernamePasswordAuthenticationToken.class.equals(authentication); 
	  } 

}
