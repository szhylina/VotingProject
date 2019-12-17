package com.myproj.vote.repos;

import org.springframework.data.repository.CrudRepository;

import com.myproj.vote.domain.VotingSystem;

public interface VotingSystemRepo extends CrudRepository<VotingSystem, Integer>{
}