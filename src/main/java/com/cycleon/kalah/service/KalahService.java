package com.cycleon.kalah.service;

import com.cycleon.kalah.domain.model.Kalah;
import com.cycleon.kalah.domain.model.Pit;
import com.cycleon.kalah.domain.model.Player;
import reactor.core.publisher.Mono;

public interface KalahService {

    /**
     * Initializes a new Kalah session with the given players.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return
     */
    Mono<Kalah> initKalah(Player firstPlayer, Player secondPlayer);

    /**
     * Modifies a Kalah by applying the Kalah rules.
     *
     * @param sessionId the sessionId of the Kalah
     * @param house     selected house
     * @return modified Kalah object
     */
    Mono<Kalah> selectHouse(String sessionId, Pit house);
}
