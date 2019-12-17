package com.myproj.vote.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.myproj.vote.domain.Voting;

public interface VotingRepo extends CrudRepository<Voting, Integer>{
	Voting findByTitle(String title);
	Optional<Voting> findById(Integer id);
}
