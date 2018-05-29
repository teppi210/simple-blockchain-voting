package com.chernikovmaksym.voting.service;

import com.chernikovmaksym.voting.blockchain.VotingContract;
import com.chernikovmaksym.voting.config.GeneralConfig;
import com.chernikovmaksym.voting.util.BlockchainUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Getter
@Slf4j
@Service
public class ContractProvider {

    private String contractAddress;

    private final Web3j web3j;
    private final GeneralConfig generalConfig;
    private final Integer ethereumSleepDuration;
    private final Integer ethereumAttempts;
    private final BigInteger gasPrice;
    private final BigInteger gasLimit;
    private final BigDecimal userEtherSupply;
    private final String adminPrivateKey;

    public ContractProvider(Web3j web3j, GeneralConfig generalConfig,
                            @Value("${ethereum.sleepDuration}") Integer ethereumSleepDuration,
                            @Value("${ethereum.attempts}") Integer ethereumAttempts,
                            @Value("${ethereum.gas-price}") BigInteger gasPrice,
                            @Value("${ethereum.gas-limit}") BigInteger gasLimit,
                            @Value("${ethereum.user-ether-supply}") BigDecimal userEtherSupply,
                            @Value("${ethereum.admin.privateKey}") String adminPrivateKey) {
        this.web3j = web3j;
        this.generalConfig = generalConfig;
        this.ethereumSleepDuration = ethereumSleepDuration;
        this.ethereumAttempts = ethereumAttempts;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.userEtherSupply = userEtherSupply;
        this.adminPrivateKey = adminPrivateKey;
    }

    public void supplyWithFunds(List<String> walletAddresses) {
        log.info("Sending ethers to users...");
        walletAddresses.forEach(walletAddress -> {
            try {
                new Transfer(web3j, getTransactionManager(adminPrivateKey))
                        .sendFunds(walletAddress, userEtherSupply, Convert.Unit.ETHER)
                        .send();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void deployContract(List<String> votersWalletAddresses) throws Exception {
        log.info("Deploying contract...");
        contractAddress = VotingContract.deploy(web3j, getTransactionManager(adminPrivateKey), gasPrice, gasLimit, votersWalletAddresses)
                .send()
                .getContractAddress();
        log.info("Contract deployed with address: {}", contractAddress);
    }

    public VotingContract loadContractFrom(String privateKey) {
        return VotingContract.load(contractAddress,
                web3j,
                getTransactionManager(privateKey),
                gasPrice,
                gasLimit);
    }

    public VotingContract loadContractFromAdmin() {
        return loadContractFrom(adminPrivateKey);
    }

    private TransactionManager getTransactionManager(String privateKey) {
        return new FastRawTransactionManager(
                web3j,
                BlockchainUtils.buildCredentials(privateKey),
                new PollingTransactionReceiptProcessor(
                        web3j,
                        ethereumSleepDuration,
                        ethereumAttempts));
    }
}
