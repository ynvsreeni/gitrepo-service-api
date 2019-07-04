package com.org.egit.app.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.org.egit.app.bean.User;
import com.org.egit.app.service.GitHubService;

public class GitHubServiceTest {
	
	String user_="ynvsreeni";
	String repo_="temp2";

	@Test
	public void getUserRepositoryTest() {				
		GitHubService gitHubService  = new GitHubService();
		User user =gitHubService.getUserRepository(user_, repo_);
		String expected = "ynvsreeni";
		assertEquals(expected, user.getUserName());	
		//add 
	}
	
	@Test
	public void getUserRepositoriesTest() {				
		GitHubService gitHubService  = new GitHubService();
		User user =gitHubService.getUserRepositories(user_);
		String expected = "ynvsreeni";		
		assertEquals(expected, user.getUserName());
		//add 
	}

	@Test
	public void getUserRepositoriesFailureTest() {				
		GitHubService gitHubService  = new GitHubService();
		User user =gitHubService.getUserRepositories(user_);
		String expected = "ynvsreeni";		
	
		//add 
	}

	
	@Test
	public void getUserRepositoryFailureTest() {				
		GitHubService gitHubService  = new GitHubService();
		User user =gitHubService.getUserRepository(user_, repo_);
		String expected = "ynvsreeni";
	//	assertEquals(expected, user.getUserName());	
		//add 
	}
}
