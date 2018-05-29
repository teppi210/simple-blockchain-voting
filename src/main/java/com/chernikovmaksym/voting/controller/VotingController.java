package com.chernikovmaksym.voting.controller;

import com.chernikovmaksym.voting.blockchain.VotingContract;
import com.chernikovmaksym.voting.model.dto.VoteRequest;
import com.chernikovmaksym.voting.service.VotingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@Controller
@Slf4j
public class VotingController {

    private final VotingService votingService;

    @Autowired
    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping(value = "/")
    public String redirectToVote() {
        return "redirect:/vote";
    }

    @GetMapping(value = "/vote")
    public String voting(Model model) {
        model.addAttribute("voteRequest", new VoteRequest());
        model.addAttribute("contractAddress", votingService.getContractAddress());
        model.addAttribute("abi", VotingContract.ABI);
        return "vote";
    }

    @PostMapping(value = "/vote")
    public String voting(@Valid @ModelAttribute VoteRequest voteRequest, Model model) throws Exception {
        model.addAttribute("voteResponse", votingService.vote(voteRequest));
        return "voteResult";
    }

    @ResponseBody
    @GetMapping(value = "/marks")
    public ResponseEntity<List<BigInteger>> getMarks() throws Exception {
        return ResponseEntity.ok(votingService.getSubmittedMarks());
    }

    @ResponseBody
    @GetMapping(value = "/voterAddresses")
    public ResponseEntity<List<BigInteger>> getVoterAddresses() throws Exception {
        return ResponseEntity.ok(votingService.getVoterAddresses());
    }

    @ResponseBody
    @GetMapping(value = "/votedAddresses")
    public ResponseEntity<List<BigInteger>> getVotedAddresses() throws Exception {
        return ResponseEntity.ok(votingService.getVotedAddresses());
    }
}
