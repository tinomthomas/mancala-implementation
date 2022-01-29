package com.cycleon.kalah.rule;

import com.cycleon.kalah.service.KalahContext;

public class SetNextPlayerRule implements KalahRule {

    @Override
    public void exert(KalahContext context) {
        var currentPit = context.getCurrentPit();
        var currentPlayer = context.getKalah().getCurrentPlayer();
        var otherPlayer = context.getKalah().getOtherPlayer();
        if (currentPit.isNotStore()) {
            context.getKalah().setCurrentPlayer(otherPlayer);
            context.getKalah().setOtherPlayer(currentPlayer);
        }
    }
}
