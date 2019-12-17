package com.myproj.vote.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "votings")
public class Voting {
	@Id
	@GeneratedValue(generator = "increment")
	private Integer id;
	private String title;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "voting_id", referencedColumnName="id")
	private Set<Candidate> candidates;
	
	public Voting() {}
	public Voting(String title) {
		this.title = title;
	}
	
	public Integer getId() {
		return id;
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Candidate> getCandidates() {
		return candidates;
	}
	public void getCandidates(Set<Candidate> c) {
		this.candidates = c;
	}
	public void addCandidate(Candidate c) {
		this.candidates.add(c);
	}
}
