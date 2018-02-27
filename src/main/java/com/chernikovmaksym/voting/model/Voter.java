package com.chernikovmaksym.voting.model;

import lombok.Data;
import org.web3j.crypto.Credentials;

@Data
public class Voter {

    private String username;
    private String password;
    private String fileName;
    private Credentials credentials;
}
