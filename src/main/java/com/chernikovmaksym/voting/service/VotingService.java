package com.chernikovmaksym.voting.service;

import com.chernikovmaksym.voting.blockchain.VotingContract;
import com.chernikovmaksym.voting.model.Voter;
import com.chernikovmaksym.voting.model.dto.VoteRequest;
import com.chernikovmaksym.voting.model.dto.VoteResponse;
import com.chernikovmaksym.voting.repository.VoterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Slf4j
public class VotingService {

    private final ContractProvider contractProvider;
    private final VoterRepository voterRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public VotingService(ContractProvider contractProvider, VoterRepository voterRepository, PasswordEncoder passwordEncoder) {
        this.contractProvider = contractProvider;
        this.voterRepository = voterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public VoteResponse vote(VoteRequest voteRequest) throws Exception {
        Voter voter = voterRepository.findByUsername(voteRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Wrong username"));

        if (!passwordEncoder.matches(voteRequest.getPassword(), voter.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        log.info("Loading contract for voter: " + voter.getUsername() + " with address: " + voter.getWalletAddress());
        VotingContract votingContract = contractProvider.loadContractFrom(voter.getPrivateKey());

        log.info("Sending vote");
        votingContract.vote(BigInteger.valueOf(voteRequest.getMark())).send();
        log.info("Mark {} successfully submitted for address: {}", voteRequest.getMark(), voter.getWalletAddress());

        return new VoteResponse(voter.getWalletAddress(), voter.getPrivateKey(), voteRequest.getMark());
    }

    public String getContractAddress() {
        return contractProvider.getContractAddress();
    }

    @SuppressWarnings("unchecked") // Contract wrapper does not support lists properly
    public List<BigInteger> getSubmittedMarks() throws Exception {
        VotingContract contract = contractProvider.loadContractFromAdmin();
        return contract.getMarks().send();
    }

    @SuppressWarnings("unchecked") // Contract wrapper does not support lists properly
    public List<BigInteger> getVoterAddresses() throws Exception {
        VotingContract contract = contractProvider.loadContractFromAdmin();
        return contract.getVotersAddresses().send();
    }

    @SuppressWarnings("unchecked") // Contract wrapper does not support lists properly
    public List<BigInteger> getVotedAddresses() throws Exception {
        VotingContract contract = contractProvider.loadContractFromAdmin();
        return contract.getVotedAddresses().send();
    }
}
