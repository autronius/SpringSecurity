package com.security.project.web.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.security.project.web.domain.Configuration;
import com.security.project.web.service.ServiceService;

@Controller
public class MainController {

	private Configuration configuration;
	private ServiceService serviceHandler;
/*	private RestfulDao rDao;*/
	
	@Autowired
	public MainController(Configuration configuration, ServiceService service){
		this.serviceHandler = service;
		this.configuration = configuration;
/*		this.rDao = rDao;*/
	}
	
	@RequestMapping ("/login")
	public ModelAndView defaultIndex(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("login");
		
		//do some things here
		
		return mav; //new ModelAndView("redirect:https://localhost:8080/SecurityProject/");
		
	}
	
	@RequestMapping ("/accessDenied")
	public ModelAndView accessDeniedPage(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("accessDenied");
		return mav;
		
	}
	@RequestMapping ({"/", "/defaultpage", "/welcome"})
	public ModelAndView defaultHTTPSIndex(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("defaultpage");
		
		//do some things here
		
		return mav;
		
	}
	
	@RequestMapping("/andrewradford")
	public String postTest(HttpServletRequest request){
		return null;
		
/*		Map data = new HashMap<String, String>();
		String returnVal = rDao.post(data);
		System.out.println(returnVal);
		return returnVal;*/
		
	}
	@RequestMapping ("/showMessage")
	public ModelAndView defaultLandingPage(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("showMessageFile");
		mav.addObject("proxyPort", configuration.getProperty("proxy.port"));
		//do some things here
		
		return mav;
		
	}
	
	@RequestMapping ("/secure/secureHomePage")
	public ModelAndView defaultSecureLandingPage(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("secureHomePage");
		
		mav.addObject("handlerResult",serviceHandler.helloFunction());
		
		return mav;
	}
	
	
} 
