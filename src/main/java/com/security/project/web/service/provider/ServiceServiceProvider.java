package com.security.project.web.service.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;

import com.security.project.web.service.ServiceService;

public class ServiceServiceProvider implements ServiceService{
    private static final Log logger = LogFactory.getLog(ServiceServiceProvider.class);
    
    /* Constructor */
    public ServiceServiceProvider(){
    }

	@Secured ({"ROLE_ADMIN"})
    public String helloFunction() {
        String successStatus = "a unique string";
       
        return successStatus;
    }
}