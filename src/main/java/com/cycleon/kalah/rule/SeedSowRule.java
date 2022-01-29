package com.cycleon.kalah.rule;

import com.cycleon.kalah.domain.model.Pit;
import com.cycleon.kalah.domain.model.Player;
import com.cycleon.kalah.domain.model.Seed;
import com.cycleon.kalah.service.KalahContext;

import java.util.ListIterator;

public class SeedSowRule implements KalahRule {

    @Override
    public void exert(KalahContext context) {
        var currentPit = context.getCurrentPit();
        var kalah = context.getKalah();
        var seedsToSow = currentPit.getSeeds();
        currentPit.emptySeeds();
        ListIterator<Seed> iterator = seedsToSow.listIterator();
        while(iterator.hasNext()){
            currentPit = kalah.getBoard().findNextPit(currentPit);
            if (canSow(kalah.getCurrentPlayer(), currentPit)) {
                currentPit.addSeed(iterator.next());
                iterator.remove();
            }
        }
        context.setCurrentPit(currentPit);
    }

    private boolean canSow(Player currentPlayer, Pit pit) {
        return (pit.belongsTo(currentPlayer) && pit.isStore()) || pit.isNotStore();
    }
}
