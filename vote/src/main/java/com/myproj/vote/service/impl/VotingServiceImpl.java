package com.myproj.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.myproj.vote.domain.Voting;
import com.myproj.vote.repos.VotingRepo;
import com.myproj.vote.service.VotingService;

public class VotingServiceImpl implements VotingService{
	@Autowired
	private VotingRepo votingRep;

	@Override
	public Voting getByTitle(String title) {
		return votingRep.findByTitle(title);
	}
}
