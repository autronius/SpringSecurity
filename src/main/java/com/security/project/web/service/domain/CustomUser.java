package com.security.project.web.service.domain;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class CustomUser implements Serializable, elasticSearchable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String[] arrAuthorities;
    
    @Autowired
    public CustomUser(){
    	this.id = UUID.randomUUID().toString();
    }
    
    public CustomUser(Map<String, Object> map)
    {
    	this.id = (String) map.get("id");
    	this.email = (String) map.get("email");
    	this.password = (String) map.get("password");
//authorities???
    }

    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public void setAuthorities(String... roles){
		this.arrAuthorities = roles;
		setAuthorities(AuthorityUtils.createAuthorityList(roles));
	}
	
	public XContentBuilder getAsJson() throws IOException{
		
		return jsonBuilder()
                .startObject()
                .field("email", this.email)
                .field("password", this.password)
            .endObject();
	}

}
