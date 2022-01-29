package com.cycleon.kalah.rule;

import com.cycleon.kalah.service.KalahContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Delegates and exert all the available Kalah rules in their defined order.
 */
@RequiredArgsConstructor
public class KalahRuleDelegate implements KalahRule {

    private final List<KalahRule> rules;

    @Override
    public void exert(KalahContext context) {
        rules.forEach(rule -> rule.exert(context));
    }
}
