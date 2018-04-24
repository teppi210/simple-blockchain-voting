package com.chernikovmaksym.voting.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CsvVoter {

    private String username;
    private String password;
    private String name;
}
