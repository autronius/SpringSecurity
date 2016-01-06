package com.security.project.web.dao.impl;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.springframework.context.ApplicationContext;
//import org.springframework.beans.factory.DisposableBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.security.project.web.service.domain.CustomUser;

//public class ElasticSearchTools implements DisposableBean{
public class ElasticSearchTools{
	private ArrayList<Node> arrNodes = new ArrayList<Node>();

	private Client client;
		
	public ElasticSearchTools() throws ElasticsearchException, IOException{
		Node newNode = nodeBuilder().client(true).node();
		arrNodes.add(0, newNode);
		client = arrNodes.get(0).client();

	}
	
	public IndexResponse insertObj(XContentBuilder jsonString, String indexToInsertInto, String objType, String uniqueId) throws ElasticsearchException, IOException{
		
		IndexResponse response = client.prepareIndex(indexToInsertInto, objType, uniqueId).setSource(jsonString).setRefresh(true).execute().actionGet();
		
		return response;
	}
	

	
	public SearchResponse findObj(String indexToSearch, String objType, String key, String value){
		SearchResponse response = client.prepareSearch(indexToSearch)
					.setTypes(objType)
					.setQuery(QueryBuilders.termQuery(key, value))
					.execute().actionGet();
		
		SearchHit[] results = response.getHits().getHits();
		
		
//		System.out.println("Current results: " + results.length);
//		for (SearchHit hit : results) {
//			System.out.println("------------------------------");
//			Map<String,Object> result = hit.getSource();  
//			System.out.println(result);
//		}
		return response;
	}	
	
	
	public DeleteByQueryResponse deleteObjByQuery(String indexName, String objType, String paramKey, String value){
//		SearchResponse sr = findObj(indexName, objType, param, value);
//		SearchHit[] results = sr.getHits().getHits();
//		String objId = "";
		
//		for (SearchHit hit : results) {
//			System.out.println("------------------------------");
//			Map<String,Object> result = hit.getSource();  
//			objId= (String) result.get("id");
//			System.out.println(result);
//		}
		
//		DeleteResponse response = client.prepareDelete(indexName, objType, objId)
//		        .setOperationThreaded(false)
//		        .setRefresh(true)
//		        .execute()
//		        .actionGet();
		DeleteByQueryResponse response = client.prepareDeleteByQuery(indexName)
		        .setQuery(QueryBuilders.termQuery(paramKey, value))
		        .execute()
		        .actionGet();
		
		return response;
	}
	
	public boolean deleteIndex(String indexName){
		DeleteIndexResponse delete = client.admin().indices().delete(new DeleteIndexRequest(indexName)).actionGet();
		return delete.isAcknowledged();
	}

	/*Shuts down (closes) all the open nodes.*/
	public void shutdown(){
		for(int i = 0; i < arrNodes.size(); i++){
			arrNodes.get(i).close();
		}
	}
 
	/* This function is used when class implements DisposableBean
	 * I'm leaving this here just in case I want to use it later.
	 * 
	 * As is, its more tightly coupled with spring by using the destroy-method property of the bean
	 * instantiation, however it also seems to provide a more graceful shutdown experience
	 * ie: no exceptions thrown. 
	 * 
	 * */
	public void destroy() throws Exception {
		shutdown();
	}
	
}
