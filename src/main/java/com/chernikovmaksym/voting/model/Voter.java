package com.chernikovmaksym.voting.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "voter", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "privateKey", "walletAddress"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String privateKey;
    private String walletAddress;
}
