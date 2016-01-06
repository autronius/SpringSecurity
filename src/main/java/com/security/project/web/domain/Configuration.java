package com.security.project.web.domain;

import java.util.Map;

public class Configuration {

	
	private Map<String, String> properties;

    public Configuration(Map<String, String> properties) {
    	this.properties = properties;
    }

    public String getProperty(String key) {
	return properties.get(key);
    }

    public boolean getBoolean(String key) {
	return Boolean.parseBoolean(getProperty(key));
    }
	
}
