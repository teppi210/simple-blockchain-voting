#!/usr/bin/env bash
solcjs ./VotingContract.sol --bin --abi --optimize -o ./
rm VotingContract.abi
rm VotingContract.bin
mv __VotingContract_sol_Voting.abi VotingContract.abi
mv __VotingContract_sol_Voting.bin VotingContract.bin
web3j solidity generate ./VotingContract.bin ./VotingContract.abi -o ../../src/main/java -p com.chernikovmaksym.voting.blockchain
