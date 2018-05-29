package com.chernikovmaksym.voting.repository;

import com.chernikovmaksym.voting.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    Optional<Voter> findByUsername(String username);
}
