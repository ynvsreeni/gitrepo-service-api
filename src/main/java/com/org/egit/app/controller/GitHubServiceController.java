package com.org.egit.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.org.egit.app.bean.User;
import com.org.egit.app.service.GitHubService;
import java.io.IOException;

@RestController
public class GitHubServiceController {
	
    @Autowired
    private GitHubService githubService;
    
    /*This service helps to provide list of project of user
     * @param user
     * @return repository
     *  
     */  
    @GetMapping(value = "/projects/{user}", produces={ MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public User getRepos(@PathVariable("user") String user) throws IOException {
    	System.out.println(" Repo's ");
        return githubService.getUserRepositories(user);
    }
  
    /*This service helps to provide user activities like list commits,contributors
     * @param user
     * @param repo
     * @return repository
     *  
     */ 
    @GetMapping(value = "/projects/{owner}/{repo}", produces={ MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public User getUserRepo(@PathVariable("owner") String owner, @PathVariable("repo") String repoName) throws IOException {
    	System.out.println(" Repo's ");
        return githubService.getUserRepository(owner,repoName);
    }
    
    
}