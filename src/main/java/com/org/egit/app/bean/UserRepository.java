package com.org.egit.app.bean;

import java.io.Serializable;

public class UserRepository implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String userName;
	String repoName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRepoName() {
		return repoName;
	}
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	
	

}
