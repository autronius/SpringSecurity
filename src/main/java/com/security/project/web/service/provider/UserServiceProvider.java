package com.security.project.web.service.provider;

import java.io.IOException;

import org.elasticsearch.ElasticsearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;

import com.security.project.web.dao.UserDao;
import com.security.project.web.service.UserService;
import com.security.project.web.service.domain.CustomUser;

public class UserServiceProvider implements UserService{

	private UserDao userDao;
	
	@Autowired
	public UserServiceProvider(UserDao userDao){
		this.userDao = userDao;
	}
	
	public CustomUser findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	public int createRegularUser(CustomUser user) {
	    user.setAuthorities("ROLE_USER");
	    int status = 0;
	    try {
			status = userDao.addUserToDb(user);
		} catch (ElasticsearchException e) {
			status = 0;
			e.printStackTrace();
		} catch (IOException e) {
			status = 0;
			e.printStackTrace();
		} 
	    return status;
	}

	public int createSuperUser(CustomUser user) {
	    user.setAuthorities("ROLE_ADMIN");
	    int status = 0;
	    try {
			status = userDao.addUserToDb(user);
		} catch (ElasticsearchException e) {
			status = 0;
			e.printStackTrace();
		} catch (IOException e) {
			status = 0;
			e.printStackTrace();
		} 
	    return status;
	}
}
