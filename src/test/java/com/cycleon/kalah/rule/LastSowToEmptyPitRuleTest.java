package com.cycleon.kalah.rule;

import com.cycleon.kalah.domain.model.Seed;
import com.cycleon.kalah.service.KalahContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LastSowToEmptyPitRuleTest extends RuleTestBase {

    @BeforeEach
    void init() {
        super.setUp();
        kalahRule = new LastSowToEmptyPitRule();
    }

    @Test
    void should_exert_rule_when_all_the_rule_conditions_satisfies(){
        //Given
        var lastSownPit = kalah.getBoard().findPitHavingIndex(5);
        lastSownPit.setSeeds(List.of(new Seed()));
        //When
        kalahRule.exert(new KalahContext(kalah, lastSownPit));
        //Then
        assertThat(kalah.getBoard().findPitHavingIndex(5).getSeeds()).isEmpty();
        assertThat(kalah.getBoard().findPitHavingIndex(9).getSeeds()).isEmpty();
        assertThat(kalah.getBoard().findFirstPlayerStore().getSeeds()).hasSize(5);
    }

    @Test
    void should_not_exert_rule_when_the_last_sown_pit_is_not_belongs_to_the_current_player(){
        //Given
        var lastSownPit = kalah.getBoard().findPitHavingIndex(9);
        lastSownPit.setSeeds(List.of(new Seed()));
        //When
        kalahRule.exert(new KalahContext(kalah, lastSownPit));
        //Then
        assertThat(kalah.getBoard().findPitHavingIndex(9).getSeeds()).hasSize(1);
        assertThat(kalah.getBoard().findPitHavingIndex(5).getSeeds()).hasSize(4);
        assertThat(kalah.getBoard().findFirstPlayerStore().getSeeds()).isEmpty();
    }

    @Test
    void should_not_exert_rule_when_the_last_sown_pit_is_a_store(){
        //Given
        var lastSownPit = kalah.getBoard().findPitHavingIndex(7);
        lastSownPit.setSeeds(List.of(new Seed()));
        //When
        kalahRule.exert(new KalahContext(kalah, lastSownPit));
        //Then
        assertThat(kalah.getBoard().findFirstPlayerStore().getSeeds()).hasSize(1);
    }

    @Test
    void should_not_exert_rule_when_all_the_opposite_pit_does_not_have_any_seeds(){
        //Given
        var lastSownPit = kalah.getBoard().findPitHavingIndex(5);
        lastSownPit.setSeeds(List.of(new Seed()));
        var oppositePit = kalah.getBoard().findPitHavingIndex(9);
        oppositePit.emptySeeds();
        //When
        kalahRule.exert(new KalahContext(kalah, lastSownPit));
        //Then
        assertThat(kalah.getBoard().findPitHavingIndex(5).getSeeds()).hasSize(1);
        assertThat(kalah.getBoard().findPitHavingIndex(9).getSeeds()).isEmpty();
        assertThat(kalah.getBoard().findFirstPlayerStore().getSeeds()).isEmpty();
    }
}
