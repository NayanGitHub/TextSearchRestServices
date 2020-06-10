package com.nayan.search.springboot.textservices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import com.nayan.search.springboot.rest.TextServicesApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TextServicesApplication.class)
@AutoConfigureMockMvc
@ContextConfiguration
public class TextServicesApplicationTests {
	
	private final static String SEARCH_URL = "/counter-api/search";
	private final static String HTTP_BASIC = "Basic " + Base64Utils.encodeToString("optus:candidates".getBytes());
	
	@Autowired
	  MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	  public void search_withoutAuth_isUnauthorized() throws Exception {
	    String searchJson = "{\"searchText\":[\"Duis\", \"Sed\", \"\", \"Augue\", \"Pellentesque\", \"abc\"]}";

	    mockMvc.perform(post(SEARCH_URL)
	      .content(searchJson)
	      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isUnauthorized());
	  }
	
	 @Test
	  public void search_withInValidParam_isUnSuccessful() throws Exception {
	    String searchJson = "{\"searchText\":[\"notme\", \"\", \"Donec\", \"Augue\", \"Pellentesque\", \"abc\"]}";

	    mockMvc.perform(
	      post(SEARCH_URL)
	        .content(searchJson)
	        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	        .header(HttpHeaders.AUTHORIZATION, HTTP_BASIC))
	      .andDo(print())
	      .andExpect(status().is4xxClientError());
	  }
	 
	 @Test
	  public void search_withEmptyParam_isNotFound() throws Exception {
	    String searchJson = "{\"searchText\":\"\"}";

	    mockMvc.perform(
	      post(SEARCH_URL)
	        .content(searchJson)
	        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	        .header(HttpHeaders.AUTHORIZATION, HTTP_BASIC))
	      .andDo(print())
	      .andExpect(status().isNotFound());
	  }
	 
	 @Test
	  public void search_withInvalidParam_isNotFound() throws Exception {
	    String searchJson = "{\"\":\"\"}";

	    mockMvc.perform(
	      post(SEARCH_URL)
	        .content(searchJson)
	        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	        .header(HttpHeaders.AUTHORIZATION, HTTP_BASIC))
	      .andDo(print())
	      .andExpect(status().isNotFound());
	  }
}
