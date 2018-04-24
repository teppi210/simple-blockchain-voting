package com.chernikovmaksym.voting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class VoteResponse {

    private String walletAddress;
    private String privateKey;
    private Integer mark;
}
