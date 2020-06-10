package com.nayan.search.springboot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonAutoDetect
public class SearchResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Map<String, String>> counts;

	public SearchResponse(ArrayList<Map<String, String>> counts) {
		super();
		this.counts = counts;
	}

	public SearchResponse() {

	}
	@JsonProperty
	public ArrayList<Map<String, String>> getCounts() {
		return counts;
	}

	public void setCounts(ArrayList<Map<String, String>> counts) {
		this.counts = counts;
	}

}
