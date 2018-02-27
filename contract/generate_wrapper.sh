solcjs ./contract/VotingContract.sol --bin --abi --optimize -o ./contract

web3j solidity generate ./contract/VotingContract.bin ./contract/VotingContract.abi -o ./src/main/java -p com.chernikovmaksym.voting.blockchain
