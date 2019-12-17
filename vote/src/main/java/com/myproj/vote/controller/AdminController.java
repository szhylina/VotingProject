package com.myproj.vote.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproj.vote.domain.Candidate;
import com.myproj.vote.domain.Voting;
import com.myproj.vote.repos.CandidateRepository;
import com.myproj.vote.repos.UserRepo;
import com.myproj.vote.repos.VotingRepo;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CandidateRepository candRep;
	
	@Autowired
	private VotingRepo votingRep;
	
	@GetMapping("/allusers")
	public String usersList(Map<String, Object> model) {
		model.put("userList", userRepo.findAll());
		return "allusers";
	}
	
	@GetMapping("/newvoting")
	public String findVotings(Map<String, Object> model) {
		Iterable<Voting> votings = votingRep.findAll();
		model.put("votings", votings);
		return "newvoting";
	}
	@PostMapping("/newvoting")
	public String addVorings(
			@RequestParam String title, 
			Map<String, Object> model) {
		Voting voting = new Voting(title);
		votingRep.save(voting);
		Iterable<Voting> votings = votingRep.findAll();
		model.put("votings", votings);
		return "newvoting";
	}	
	@GetMapping("/selectvoting")
	public String findCandidates(Map<String, Object> model) {
		Iterable<Voting> votings = votingRep.findAll();
		model.put("votings", votings);
		return "selectvoting";
	}
	@PostMapping("/selectvoting")
	public String selectVoting(
			@RequestParam("voting_id") Voting voting, 
			Map<String, Object> model) {	
		return "redirect:/newcandidat{voting}";
	}
	@GetMapping("/newcandidat")
	public String findCandidatesOfVoting(
			@RequestParam String voting,
			Map<String, Object> model) {
		Optional<Voting> v = votingRep.findById(Integer.decode(voting));
		if (v.isPresent()){
			model.put("votings", v.get());
			model.put("candidates", v.get().getCandidates());
			}
		return "newcandidat";
	}
	@PostMapping("/newcandidat")
	public String addCandidates(
			@RequestParam String voting,
			@RequestParam String name, 
			Map<String, Object> model) {
		Candidate cFromDatabase = candRep.findByName(name);
		if (cFromDatabase == null) {
			Candidate c = new Candidate(name);
			c.setVoting_id(Integer.decode(voting));
			candRep.save(c);
		}
		return "redirect:/selectvoting";
	}
}