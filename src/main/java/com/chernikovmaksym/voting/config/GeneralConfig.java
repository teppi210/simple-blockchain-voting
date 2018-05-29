package com.chernikovmaksym.voting.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class GeneralConfig {

    @Value("${ethereum.url}")
    public String ethereumUrl;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(ethereumUrl, new OkHttpClient(), false));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
