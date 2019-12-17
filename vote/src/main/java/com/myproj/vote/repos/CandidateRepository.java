package com.myproj.vote.repos;

import org.springframework.data.repository.CrudRepository;
import com.myproj.vote.domain.Candidate;
import com.myproj.vote.domain.Voting;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
	Candidate findByName(String name);
}
