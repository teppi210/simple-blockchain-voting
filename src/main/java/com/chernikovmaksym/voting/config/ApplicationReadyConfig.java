package com.chernikovmaksym.voting.config;

import com.chernikovmaksym.voting.model.CsvVoter;
import com.chernikovmaksym.voting.model.Voter;
import com.chernikovmaksym.voting.repository.VoterRepository;
import com.chernikovmaksym.voting.service.ContractProvider;
import com.chernikovmaksym.voting.util.BlockchainUtils;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.web3j.crypto.Credentials;

import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class ApplicationReadyConfig {

    @Value("${voters.fileName}")
    private String votersFilename;

    private final VoterRepository voterRepository;
    private final ContractProvider contractProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationReadyConfig(VoterRepository voterRepository,
                                  ContractProvider contractProvider,
                                  PasswordEncoder passwordEncoder) {
        this.contractProvider = contractProvider;
        this.voterRepository = voterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() throws Exception {
        try {
            List<Voter> voters = generateVotersData(readVotersFromFile());
            voterRepository.saveAll(voters);
            List<String> voterWalletAddresses = voters.stream()
                    .map(Voter::getWalletAddress)
                    .collect(Collectors.toList());
            contractProvider.supplyWithFunds(voterWalletAddresses);
            contractProvider.deployContract(voterWalletAddresses);
        } catch (Exception e) {
            log.error("Error on application startup:", e);
            throw e;
        }
    }

    private List<Voter> generateVotersData(List<CsvVoter> csvVoters) {
        return csvVoters.stream()
                .map(this::generateVoterData)
                .collect(Collectors.toList());
    }

    private Voter generateVoterData(CsvVoter csvVoter) {
        Voter voter = new Voter();
        voter.setUsername(csvVoter.getUsername());
        voter.setPassword(passwordEncoder.encode(csvVoter.getPassword()));
        voter.setName(csvVoter.getName());
        Credentials credentials = BlockchainUtils.generateCredentials();
        voter.setPrivateKey(credentials.getEcKeyPair().getPrivateKey().toString());
        voter.setWalletAddress(credentials.getAddress());
        return voter;
    }

    @SuppressWarnings("unchecked")
    private List<CsvVoter> readVotersFromFile() {
        try {
            log.info("Reading voters from file...");
            HeaderColumnNameMappingStrategy<CsvVoter> beanStrategy = new HeaderColumnNameMappingStrategy<>();
            beanStrategy.setType(CsvVoter.class);
            CsvToBean<CsvVoter> csvToBean = new CsvToBeanBuilder(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(votersFilename)))
                    .withMappingStrategy(beanStrategy)
                    .withType(CsvVoter.class)
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            log.error("Unhandled error during .csv file reading:", e);
            throw new RuntimeException(e);
        }
    }
}
