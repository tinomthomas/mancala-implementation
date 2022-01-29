package com.cycleon.kalah.service;

import com.cycleon.kalah.domain.model.Kalah;
import com.cycleon.kalah.domain.model.Pit;
import com.cycleon.kalah.domain.model.Player;
import com.cycleon.kalah.domain.repo.KalahRepo;
import com.cycleon.kalah.rule.KalahRuleDelegate;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class KalahServiceImpl implements KalahService {

    private final KalahRepo kalahRepo;

    private final KalahRuleDelegate ruleDelegate;

    @Override
    public Mono<Kalah> initKalah(Player firstPlayer, Player secondPlayer) {
        return kalahRepo.create(firstPlayer, secondPlayer);
    }

    @Override
    public Mono<Kalah> selectHouse(String sessionId, Pit house) {
        return kalahRepo.get(sessionId)
            .map(kalah -> toContext(kalah, house))
            .doOnNext(ruleDelegate::exert)
            .map(KalahContext::getKalah)
            .doOnSuccess(kalahRepo::update);
    }

    private KalahContext toContext(Kalah kalah, Pit pit) {
        return new KalahContext(kalah, kalah.getBoard().findPitHavingIndex(pit.getIndex()));
    }
}
