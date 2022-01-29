package com.cycleon.kalah.domain.repo;

import com.cycleon.kalah.domain.model.Kalah;
import com.cycleon.kalah.domain.model.Player;
import reactor.core.publisher.Mono;

public interface KalahRepo {

    Mono<Kalah> create(Player firstPlayer, Player secondPlayer);

    Mono<Kalah> get(String sessionId);

    Mono<Kalah> update(Kalah kalah);
}
