package com.security.project.web.service;

import org.springframework.security.access.annotation.Secured;

public interface ServiceService {
	
	@Secured ({"ROLE_ADMIN"})
    public String helloFunction();
    
    
}