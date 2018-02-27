package com.chernikovmaksym.voting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ContractStorage {

    private final Set<Voter> voters = new HashSet<>();
    private String contractAddress;
}
