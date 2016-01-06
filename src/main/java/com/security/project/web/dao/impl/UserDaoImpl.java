package com.security.project.web.dao.impl;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.security.project.web.dao.UserDao;
import com.security.project.web.service.domain.CustomUser;
import com.security.project.web.service.domain.elasticSearchable;


/*
 * Provides functions for connecting to the data source.
 * 
 */
public class UserDaoImpl implements UserDao{

	ElasticSearchTools est;
	
	public UserDaoImpl(ElasticSearchTools est){
		this.est = est;
	}
	
	public CustomUser findUserByEmail(String username) {
		SearchResponse response = est.findObj(CustomUser.indexName,  CustomUser.objType, "email", username);
		SearchHit[] results = response.getHits().getHits();
		CustomUser user = new CustomUser();
		
		for (SearchHit hit : results) {
//			System.out.println("------------------------------");
			user = new CustomUser(hit.getSource());  
//			System.out.println(result);
		}
		System.out.println(user.toString());
		return user;
	}

	public int addUserToDb(elasticSearchable user) throws ElasticsearchException, IOException {
		CustomUser bobUser = new CustomUser();
		bobUser.setEmail("bob");
		bobUser.setPassword("bobspassword");
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		bobUser.setAuthorities(authorities);
		
		est.insertObj(bobUser.getAsJson(), "usersindex", "user", user.getId());
		return 0;
	}

}
