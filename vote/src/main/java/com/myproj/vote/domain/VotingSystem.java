package com.myproj.vote.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "voting_systems")
public class VotingSystem {
	@Id
	@GeneratedValue(generator = "increment")
	private Integer id;
	
	private String voting_id;
	private String user_id;
	private String candidate_id;
	
	public VotingSystem() {}
	public VotingSystem(String voting_id, String user_id, String candidate_id) {
		this.voting_id = voting_id;
		this.user_id = user_id;
		this.candidate_id = candidate_id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVoting_id() {
		return voting_id;
	}
	public void setVoting_id(String voting_id) {
		this.voting_id = voting_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCandidate_id() {
		return candidate_id;
	}
	public void setCandidate_id(String candidate_id) {
		this.candidate_id = candidate_id;
	}
}