package com.cycleon.kalah.rule;

import com.cycleon.kalah.exception.InvalidSelectionException;
import com.cycleon.kalah.service.KalahContext;

public class FirstSelectionOnEmptyPitRule implements KalahRule {

    @Override
    public void exert(KalahContext context) {
        if (context.getCurrentPit().getSeeds().isEmpty()) {
            throw new InvalidSelectionException("You cannot select an empty house.");
        }
    }
}
