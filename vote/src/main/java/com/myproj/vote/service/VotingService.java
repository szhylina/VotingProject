package com.myproj.vote.service;

import com.myproj.vote.domain.Voting;

public interface VotingService {
	Voting getByTitle(String title);
}
