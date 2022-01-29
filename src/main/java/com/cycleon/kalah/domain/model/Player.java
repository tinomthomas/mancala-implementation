package com.cycleon.kalah.domain.model;

import lombok.Value;

@Value
public class Player {
    String firstName;
    String lastName;
    String userName;
    Position position;
}
