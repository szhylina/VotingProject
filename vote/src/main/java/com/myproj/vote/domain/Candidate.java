package com.myproj.vote.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidates")
public class Candidate {
  @Id
  @GeneratedValue(generator = "increment")
  private Integer id;
  private String name;
  private Integer voices;
  
  private Integer voting_id;
  
  public Candidate(){}
  
  public Candidate(String name)
  {
	  this.name = name;
	  this.voices = 0;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getVoices() {
    return voices;
  }
  public void setVoices(Integer voices) {
    this.voices = voices;
  }
  public void addVoice(){
	  this.voices = this.voices + 1;
  }
  public void removeVoice() {
	  this.voices = this.voices - 1;
  }

public Integer getVoting_id() {
	return voting_id;
}

public void setVoting_id(Integer voting_id) {
	this.voting_id = voting_id;
}
}