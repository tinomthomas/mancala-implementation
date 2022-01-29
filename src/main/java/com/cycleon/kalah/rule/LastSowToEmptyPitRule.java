package com.cycleon.kalah.rule;

import com.cycleon.kalah.domain.model.Pit;
import com.cycleon.kalah.service.KalahContext;

public class LastSowToEmptyPitRule implements KalahRule {

    @Override
    public void exert(KalahContext context) {
        var currentPit = context.getCurrentPit();
        var board = context.getKalah().getBoard();
        if (currentPit.isNotStore()
                && currentPit.belongsTo(context.getKalah().getCurrentPlayer())
                && currentPit.isSingleSeedPit()) {
            Pit oppositePit = board.findOppositePitOf(currentPit);
            if (oppositePit.containSeeds()) {
                moveCurrentAndOppositePitSeedsToCurrentPlayerStore(oppositePit, context);
            }
        }
    }

    private void moveCurrentAndOppositePitSeedsToCurrentPlayerStore(Pit oppositePit, KalahContext context){
        var currentPit = context.getCurrentPit();
        var currentPlayerStore = context.getKalah().getBoard().findStoreOf(context.getKalah().getCurrentPlayer());
        currentPlayerStore.getSeeds().addAll(oppositePit.getSeeds());
        currentPlayerStore.getSeeds().addAll(currentPit.getSeeds());
        oppositePit.emptySeeds();
        currentPit.emptySeeds();
    }
}
