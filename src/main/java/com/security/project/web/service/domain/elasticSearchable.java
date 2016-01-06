package com.security.project.web.service.domain;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;

public interface elasticSearchable {
	public static final String objType = "user";
	public static final String indexName = "usersindex";
	
	XContentBuilder getAsJson() throws IOException;
	String getId();
	
}
