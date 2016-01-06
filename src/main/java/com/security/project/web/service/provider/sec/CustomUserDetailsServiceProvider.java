package com.security.project.web.service.provider.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.security.project.web.dao.UserDao;
import com.security.project.web.service.domain.CustomUser;


/*
 * If using this class, add this authentication manager to security-config.xml
 * <authentication-manager> 
		<authentication-provider user-service-ref="customUserDetailsServiceProvider"/>  <!-- if still using the built-in UserDetailsService-->
	</authentication-manager>
 *
 * This class implements the UserDetailsService, and provides for some customisation of the login process, such as custom user object definitions.
 * When authentication provider uses a user-service, it automatically calls the function "loadUserByUsername" and authenticates against it the user
 * we bring back from our data source. 
 * 
 * 
 */
@Component
public class CustomUserDetailsServiceProvider implements UserDetailsService{

	private final UserDao uDao; 

	@Autowired 
	public CustomUserDetailsServiceProvider(UserDao uDao) { 
		this.uDao = uDao;
	} 

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		CustomUser user = uDao.findUserByEmail(username); 
		if (user == null) 
		{ 
			throw new UsernameNotFoundException("Invalid username/password.");
		}
		
		return new CustomUserDetails(user);
	}
	
	private final class CustomUserDetails extends CustomUser implements UserDetails { 
		private static final long serialVersionUID = 1L;

		CustomUserDetails(CustomUser user) 
		{ 
			setId(user.getId()); 
			setEmail(user.getEmail()); 
			setPassword(user.getPassword()); 
		}
	
		public String getUsername() {
			return getEmail();
		}
	
		public boolean isAccountNonExpired() {
			return true;
		}
	
		public boolean isAccountNonLocked() {
			return true;
		}
	
		public boolean isCredentialsNonExpired() {
			return true;
		}
	
		public boolean isEnabled() {
			return true;
		}
	}
}
