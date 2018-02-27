package com.chernikovmaksym.voting.controller;

import com.chernikovmaksym.voting.model.ContractStorage;
import com.chernikovmaksym.voting.model.dto.VoteRequest;
import com.chernikovmaksym.voting.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class VotingController {

    private final VotingService votingService;
    private final ContractStorage contractStorage;

    @Autowired
    public VotingController(VotingService votingService, ContractStorage contractStorage) {
        this.votingService = votingService;
        this.contractStorage = contractStorage;
    }

    @GetMapping(value = "/vote")
    public String voting(Model model) {
        model.addAttribute("voteRequest", new VoteRequest());
        model.addAttribute("contractAddress", contractStorage.getContractAddress());
        return "vote";
    }

    @PostMapping(value = "/vote")
    public String voting(@Valid @ModelAttribute VoteRequest voteRequest) throws Exception {
        votingService.vote(voteRequest);
        return "result";
    }
}
