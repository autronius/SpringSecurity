package com.security.project.web.dao;

import java.io.IOException;

import org.elasticsearch.ElasticsearchException;

import com.security.project.web.service.domain.CustomUser;
import com.security.project.web.service.domain.elasticSearchable;

public interface UserDao {

	CustomUser findUserByEmail(String username);

	int addUserToDb(elasticSearchable user) throws ElasticsearchException, IOException;
}
