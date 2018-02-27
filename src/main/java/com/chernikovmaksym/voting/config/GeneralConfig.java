package com.chernikovmaksym.voting.config;

import com.chernikovmaksym.voting.model.ContractStorage;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

@Configuration
public class GeneralConfig {

    public static final BigInteger GAS_PRICE = new BigInteger("20000000000");
    public static final BigInteger GAS_LIMIT = new BigInteger("4300000");

    @Value("${ethereum.url}")
    private String ethereumUrl;

    @Value("${ethereum.admin.password}")
    private String ethereumAdminPassword;

    @Value("${ethereum.admin.filePath}")
    private String ethereumAdminFilePath;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(ethereumUrl, new OkHttpClient(), false));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ContractStorage voterStorage() {
        return new ContractStorage();
    }

    @Bean
    public Credentials adminCredentials() throws Exception {
        return WalletUtils.loadCredentials(ethereumAdminPassword, ethereumAdminFilePath);
    }
}
