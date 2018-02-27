package com.chernikovmaksym.voting.config;

import com.chernikovmaksym.voting.blockchain.VotingContract;
import com.chernikovmaksym.voting.model.ContractStorage;
import com.chernikovmaksym.voting.model.Voter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.io.File;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class ApplicationReadyConfig {

    @Value("${voters}")
    private String[] rawVotersData;

    @Value("${ethereum.wallets.filePath}")
    private String ethereumWalletsFilePath;

    private final ContractStorage contractStorage;
    private final Web3j web3j;
    private final Credentials adminCredentials;

    public ApplicationReadyConfig(ContractStorage contractStorage, Web3j web3j, Credentials adminCredentials) {
        this.contractStorage = contractStorage;
        this.web3j = web3j;
        this.adminCredentials = adminCredentials;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() throws Exception {
        parseRawVotersData();
        generateWalletsForVoters();

        log.info("Deploying contract");
        String contractAddress = VotingContract.deploy(
                web3j,
                new FastRawTransactionManager(
                        web3j,
                        adminCredentials,
                        new PollingTransactionReceiptProcessor(
                                web3j,
                                1000,
                                10)),
                GeneralConfig.GAS_PRICE,
                GeneralConfig.GAS_LIMIT,
                contractStorage.getVoters()
                        .parallelStream()
                        .map(voter -> voter.getCredentials().getAddress())
                        .collect(Collectors.toList()))

                .sendAsync()
                .get()
                .getContractAddress();
        contractStorage.setContractAddress(contractAddress);
        log.info("Contract deployed. Contract address: " + contractAddress);
    }

    private void parseRawVotersData() {
        log.info("Parsing voters data");
        for (int i = 0; i < rawVotersData.length; i = i + 2) {
            Voter voter = new Voter();
            voter.setUsername(rawVotersData[i]);
            voter.setPassword(rawVotersData[i + 1]);
            contractStorage.getVoters().add(voter);
        }
        log.info("Voters data parsed");
    }

    private void generateWalletsForVoters() {
        log.info("Generating voters wallets");
        contractStorage.getVoters().forEach(voter -> {
            try {
                String walletFileName = WalletUtils.generateFullNewWalletFile(voter.getPassword(), new File(ethereumWalletsFilePath));
                voter.setFileName(walletFileName);
                voter.setCredentials(WalletUtils.loadCredentials(voter.getPassword(), ethereumWalletsFilePath + walletFileName));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Wallets generated");
    }
}
