package com.cycleon.kalah.domain.model;

import com.cycleon.kalah.configuration.BoardConfiguration;
import lombok.Getter;

@Getter
public class BoardSettings {

    private final int initialHouseSeeds;
    private final int firstPlayerStoreIndex;
    private final int secondPlayerStoreIndex;
    private final int houseCount;

    public BoardSettings(BoardConfiguration boardConfiguration) {
        this.houseCount = boardConfiguration.getHouse().getCount();
        this.initialHouseSeeds = boardConfiguration.getHouse().getInitialSeeds();
        this.firstPlayerStoreIndex = this.houseCount + 1;
        this.secondPlayerStoreIndex = 2 * this.firstPlayerStoreIndex;
    }
}
