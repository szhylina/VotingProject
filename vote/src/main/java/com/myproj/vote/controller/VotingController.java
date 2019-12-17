package com.myproj.vote.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.myproj.vote.domain.*;
import com.myproj.vote.repos.*;


@Controller
public class VotingController {
	@Autowired
	private VotingRepo votingRep;
	@Autowired
	private CandidateRepository candRep;
	@Autowired
	private VotingSystemRepo vsRep;
	
	@GetMapping("/selectvotinguser")
	public String goToVote(Map<String, Object> model){
		Iterable<Voting> votings = votingRep.findAll();
		model.put("votings", votings);
		return "selectvotinguser";
	}
	
	@PostMapping("/selectvotinguser")
	public String chooseCandidate(
			@RequestParam("voting_id") Voting voting,
			Map<String, Object> model) {
		return "redirect:/voting{voting}";
	}
	
	@GetMapping("/voting")
	public String goToChoseCandidate(
			@RequestParam String voting,
			Map<String, Object> model){
		Optional<Voting> v = votingRep.findById(Integer.decode(voting));
		if (v.isPresent()){
			model.put("candidates", v.get().getCandidates());
			model.put("voting", v);
		}
		return "voting";
	}	
	@PostMapping("/voting")
	public String chooseCandidateForVoting(
			@RequestParam String candidate,
			Map<String, Object> model) {
		Iterable<VotingSystem> allVoices = vsRep.findAll();
		User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		Optional<Candidate> c = candRep.findById(Integer.decode(candidate));
		if (c.isPresent()) {
			Candidate t = c.get();
			String currentVotingId = t.getVoting_id().toString();
			for (VotingSystem v: allVoices)	{
				if (v.getUser_id().equals(u.getId().toString())) {
					if(v.getVoting_id().equals(currentVotingId)) {
						if (v.getCandidate_id().equals(candidate)) {
							return "redirect:/selectvotinguser";
						}
						else {
							Optional<Candidate> c2 = candRep.findById(Integer.decode(v.getCandidate_id()));
							if (c2.isPresent()) {
								countVoices();
							}
							t.addVoice();
							candRep.save(t);
							v.setCandidate_id(candidate);
							return "redirect:/selectvotinguser";
						}
					}
				}
			}
			t.addVoice();
			candRep.save(t);
			VotingSystem vs = new VotingSystem(
					t.getVoting_id().toString(), 
					u.getId().toString(),
					t.getId().toString());
			vsRep.save(vs);
		}
		return "redirect:/selectvotinguser";
	}
	@GetMapping("/result")
	public String seeResults(Map<String, Object> model){
		Iterable<Voting> votings = votingRep.findAll();
		model.put("votings", votings);
		return "result";
	}
	
	public void countVoices()
	{
		Integer voices = 0;
		Iterable<VotingSystem> allVoices = vsRep.findAll();
		Iterable<Candidate> allCand = candRep.findAll();
		for (Candidate c: allCand) {
			voices = 0;
			for (VotingSystem v: allVoices)	{
				if (v.getId().equals(c.getVoting_id()))
					voices = voices + 1;
				c.setVoices(voices);
				candRep.save(c);
			}
		}
	}
}
