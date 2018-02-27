package com.chernikovmaksym.voting.config;

import com.chernikovmaksym.voting.model.ContractStorage;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.math.BigInteger;

@Configuration
public class GeneralConfig {

    public static final BigInteger GAS_PRICE = new BigInteger("20000000000");
    public static final BigInteger GAS_LIMIT = new BigInteger("4300000");

    @Value("${ethereum.url}")
    public String ethereumUrl;

    @Value("${ethereum.admin.password}")
    public String ethereumAdminPassword;

    @Value("${ethereum.admin.filePath}")
    public String ethereumAdminFilePath;

    @Value("${ethereum.sleepDuration}")
    public Integer ethereumSleepDuration;

    @Value("${ethereum.attempts}")
    public Integer ethereumAttempts;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(ethereumUrl, new OkHttpClient(), false));
    }

    @Bean
    public ContractStorage voterStorage() {
        return new ContractStorage();
    }

    @Bean
    public Credentials adminCredentials() throws Exception {
        return WalletUtils.loadCredentials(ethereumAdminPassword, ethereumAdminFilePath);
    }

    @Bean
    public TransactionManager transactionManager(@Autowired Web3j web3j, @Autowired Credentials adminCredentials) {
        return new FastRawTransactionManager(
                web3j,
                adminCredentials,
                new PollingTransactionReceiptProcessor(
                        web3j,
                        ethereumSleepDuration,
                        ethereumAttempts));
    }
}
