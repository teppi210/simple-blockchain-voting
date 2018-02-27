package com.chernikovmaksym.voting.service;

import com.chernikovmaksym.voting.blockchain.VotingContract;
import com.chernikovmaksym.voting.config.GeneralConfig;
import com.chernikovmaksym.voting.model.ContractStorage;
import com.chernikovmaksym.voting.model.Voter;
import com.chernikovmaksym.voting.model.dto.VoteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.math.BigInteger;
import java.util.Optional;

@Service
@Slf4j
public class VotingService {

    private final ContractStorage contractStorage;
    private final Web3j web3j;
    private final GeneralConfig generalConfig;

    @Autowired
    public VotingService(ContractStorage contractStorage, Web3j web3j, GeneralConfig generalConfig) {
        this.contractStorage = contractStorage;
        this.web3j = web3j;
        this.generalConfig = generalConfig;
    }

    public void vote(VoteRequest voteRequest) throws Exception {
        Optional<Voter> maybeExistingVoter = contractStorage.getVoters()
                .parallelStream()
                .filter(voter -> voter.getUsername().equals(voteRequest.getUsername()))
                .filter(voter -> voter.getPassword().equals(voteRequest.getPassword()))
                .findFirst();

        if (!maybeExistingVoter.isPresent()) {
            throw new RuntimeException("Wrong username or password");
        }

        Voter voter = maybeExistingVoter.get();

        log.info("Loading contract for voter: " + voter.getUsername() + " with address: " + voter.getCredentials().getAddress());
        VotingContract votingContract = VotingContract.load(contractStorage.getContractAddress(), web3j,

                new FastRawTransactionManager(
                        web3j,
                        voter.getCredentials(),
                        new PollingTransactionReceiptProcessor(
                                web3j,
                                generalConfig.ethereumSleepDuration,
                                generalConfig.ethereumAttempts)),
                GeneralConfig.GAS_PRICE,
                GeneralConfig.GAS_LIMIT);

        log.info("Sending vote");
        votingContract.vote(BigInteger.valueOf(voteRequest.getMark())).send();
    }
}
