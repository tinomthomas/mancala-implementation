package com.cycleon.kalah.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Pit {
    private List<Seed> seeds;
    private int index;
    private Player player;
    private boolean store;

    public void addSeed(Seed seed) {
        seeds.add(seed);
    }

    public void emptySeeds() {
        this.seeds = new ArrayList<>();
    }

    public boolean belongsTo(Player player) {
        return this.player.getUserName().equals(player.getUserName());
    }

    public boolean isNotStore() {
        return !store;
    }

    public boolean isSingleSeedPit() {
        return seeds.size() == 1;
    }

    public boolean containSeeds() {
        return !seeds.isEmpty();
    }
}
