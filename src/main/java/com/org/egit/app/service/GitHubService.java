package com.org.egit.app.service;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.org.egit.app.bean.Repo;
import com.org.egit.app.bean.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GitHubService.class);
	
	 /*This service helps to provide user activities like list commits,contributors
     * @param user
     * @param repo
     * @return repository
     *  
     */
	public User getUserRepository(String user, String repo) {
		  LOGGER.info("getUserRepository");
		RepositoryService repositoryService = new RepositoryService();
		final CommitService commitService = new CommitService();
		ContentsService contentsService = new ContentsService();
		User user_ = new User();
		List<Repo> repos = new ArrayList<>();
		List<com.org.egit.app.bean.Contributor> contributors_ = new ArrayList<>();
		Repo repo_ = new Repo();
		com.org.egit.app.bean.Contributor contributor_;
		try {
			Repository r = repositoryService.getRepository(user, repo);
			user_.setUserName(user);
			//to get list of Contributors
			List<Contributor> contributors = repositoryService.getContributors(r, false);	
			for (Contributor contributor : contributors) {
				contributor_ = new com.org.egit.app.bean.Contributor();
				contributor_.setId(contributor.getId());
				contributor_.setName(contributor.getName());
				contributor_.setType(contributor.getType());
				contributor_.setUrl(contributor.getUrl());
				contributors_.add(contributor_);
			}
			LOGGER.info("Contributors size : "+contributors_.size());
			//to get repository commits
			List<RepositoryCommit> repositoryCommits = commitService.getCommits(r);		
			repo_.setCommits(repositoryCommits.size());
			LOGGER.info("Repository commits : "+repositoryCommits.size());
			//to get readme content from repo
			RepositoryContents repositoryContents = contentsService.getReadme(r);
			String readme = new String(Base64.decodeBase64(repositoryContents.getContent().getBytes()));
			LOGGER.info("README : "+readme);
			repo_.setReadme(readme);
			repo_.setContributors(contributors_);
			repos.add(repo_);
			user_.setRepos(repos);
		} catch (Exception e) {			
			   LOGGER.error("Exception occured @ getUserRepository() invocation ",e);
		}
		return user_;
	}

	/*This service helps to provide list of project of user
     * @param user
     * @return repository
     *  
     */ 
	public User getUserRepositories(String user) {
		LOGGER.info(" getUserRepositories()");
		RepositoryService repositoryService = new RepositoryService();
		User user_ = new User();
		List<Repo> repos = new ArrayList<>();
		Repo repo = null;
		try {
			List<Repository> repositories = repositoryService.getRepositories(user);
			user_.setUserName(user);
			for (Repository r : repositories) {
				repo = new Repo();
				repo.setId(r.getId());
				repo.setTitle(r.getDescription());
				repo.setUrl(r.getGitUrl());
				repo.setName(r.getName());
				repos.add(repo);
			}
			user_.setRepos(repos);
			LOGGER.info(" User Repository : "+user_);
		} catch (IOException e) {
			   LOGGER.error("Exception occured @ getUserRepositories() invocation ",e);
		}
		return user_;
	}

}