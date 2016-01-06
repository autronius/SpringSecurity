package com.security.project.web.service;

import com.security.project.web.service.domain.CustomUser;

public interface UserService {

	
	public CustomUser findUserByEmail(String email);
	public int createRegularUser(CustomUser user);
	public int createSuperUser(CustomUser user);
	
}
