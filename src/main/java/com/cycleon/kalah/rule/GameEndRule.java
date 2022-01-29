package com.cycleon.kalah.rule;

import com.cycleon.kalah.domain.model.Pit;
import com.cycleon.kalah.service.KalahContext;

public class GameEndRule implements KalahRule {

    @Override
    public void exert(KalahContext context) {
        var board = context.getKalah().getBoard();
        var currentPlayerTotalSeeds = board.findTotalSeedsInHousesOf(context.getKalah().getCurrentPlayer());
        var otherPlayerTotalSeeds = board.findTotalSeedsInHousesOf(context.getKalah().getOtherPlayer());
        if (currentPlayerTotalSeeds.isEmpty() || otherPlayerTotalSeeds.isEmpty()) {

            Pit currentPlayerStore = board.findStoreOf(context.getKalah().getCurrentPlayer());
            currentPlayerStore.getSeeds().addAll(currentPlayerTotalSeeds);

            Pit otherPlayerStore = board.findStoreOf(context.getKalah().getOtherPlayer());
            otherPlayerStore.getSeeds().addAll(otherPlayerTotalSeeds);

            context.getKalah().setWinner(currentPlayerStore.getSeeds().size() > otherPlayerStore.getSeeds().size()
                ? currentPlayerStore.getPlayer() : otherPlayerStore.getPlayer());
        }
    }
}
