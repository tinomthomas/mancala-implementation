package com.cycleon.kalah.domain.model;

import lombok.Value;

import java.util.List;
import java.util.Random;

@Value
public class Seed {
    private static final Random RANDOM = new Random();
    private static final List<String> TYPES = List.of("type1", "type2", "type3", "type4", "type5", "type6");
    String type;

    public Seed() {
        this.type = TYPES.get(RANDOM.nextInt(TYPES.size()));
    }
}
