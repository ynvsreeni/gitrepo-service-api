package com.org.egit.app.test;

import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.org.egit.app.bean.User;
import com.org.egit.app.controller.GitHubServiceController;
import com.org.egit.app.service.GitHubService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GitHubServiceController.class, secure = false)
public class GitHubServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GitHubService gitHubService;
	
	User mockUser = new User("ynvsreeni");	

	@Test
	public void getUserReposTest() throws Exception {
		Mockito.when(gitHubService.getUserRepositories(Mockito.anyObject())).thenReturn(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(	"/projects/ynvsreeni").accept(	MediaType.APPLICATION_JSON);
		String expected = "{\"userName\":\"ynvsreeni\"}";
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	@Ignore
	public void getUserReposTestXML() throws Exception {
		Mockito.when(gitHubService.getUserRepositories(Mockito.anyObject())).thenReturn(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(	"/projects/ynvsreeni").accept(	MediaType.APPLICATION_XML_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(" = result======== "+result.getResponse().getContentAsString());		
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><userName>ynvsreeni</userName></user>";
		System.out.println("  === "+result.getResponse().toString());
		//assertEquals(expected, result.getResponse().getContentAsString(), false);			
	}

	
	@Test
	public void getUserRepoTest() throws Exception {
		Mockito.when(gitHubService.getUserRepository(Mockito.anyString(),Mockito.anyString())).thenReturn(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(	"/projects/ynvsreeni/temp2").accept(MediaType.APPLICATION_JSON);
		String expected = "{\"userName\":\"ynvsreeni\"}";
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("  RE========== "+result.getResponse().getContentAsString());
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	@Ignore
	public void getUserRepoTestXML() throws Exception {
		Mockito.when(gitHubService.getUserRepository(Mockito.anyString(),Mockito.anyString())).thenReturn(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(	"/projects/ynvsreeni/temp2").accept(	MediaType.APPLICATION_XML_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(" = result======== "+result.getResponse().getContentAsString());		
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><userName>ynvsreeni</userName></user>";
		System.out.println("  === "+result.getResponse().toString());
		//assertEquals(expected, result.getResponse().getContentAsString(), false);			
	}


}
