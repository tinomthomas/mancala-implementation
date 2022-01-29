package com.cycleon.kalah.domain.repo;

import com.cycleon.kalah.configuration.BoardConfiguration;
import com.cycleon.kalah.domain.model.Kalah;
import com.cycleon.kalah.domain.model.Player;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class OnHeapKalahRepo implements KalahRepo {

    private static final Map<String, Kalah> KALAH_MAP = new HashMap<>();
    private final BoardConfiguration settings;

    @Override
    public Mono<Kalah> create(Player firstPlayer, Player secondPlayer) {
        return Mono.just(new Kalah(firstPlayer, secondPlayer, settings))
            .doOnSuccess(k -> KALAH_MAP.put(k.getSessionId(), k));
    }

    @Override
    public Mono<Kalah> get(String sessionId) {
        return Mono.fromCallable(() -> KALAH_MAP.get(sessionId));
    }

    @Override
    public Mono<Kalah> update(Kalah kalah) {
        return Mono.just(kalah).doOnSuccess(k -> KALAH_MAP.put(k.getSessionId(), k));
    }
}
