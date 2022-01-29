package com.cycleon.kalah.rule;

import com.cycleon.kalah.service.KalahContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SetNextPlayerRuleTest extends RuleTestBase {

    @BeforeEach
    void init() {
        super.setUp();
        kalahRule = new SetNextPlayerRule();
    }

    @Test
    void should_set_next_player_when_the_last_sown_pit_is_house_and_not_belongs_to_current_player(){
        //Given
        var lastSownPit = kalah.getBoard().findPitHavingIndex(9);
        //When
        assertThat(kalah.getCurrentPlayer()).isEqualTo(FIRST_PLAYER);
        assertThat(kalah.getOtherPlayer()).isEqualTo(SECOND_PLAYER);
        kalahRule.exert(new KalahContext(kalah, lastSownPit));
        //Then
        assertThat(kalah.getCurrentPlayer()).isEqualTo(SECOND_PLAYER);
        assertThat(kalah.getOtherPlayer()).isEqualTo(FIRST_PLAYER);
    }

    @Test
    void should_keep_the_current_player_as_next_player_when_the_last_sown_pit_is_a_store(){
        //Given
        var lastSownPit = kalah.getBoard().findPitHavingIndex(7);
        //When
        assertThat(kalah.getCurrentPlayer()).isEqualTo(FIRST_PLAYER);
        assertThat(kalah.getOtherPlayer()).isEqualTo(SECOND_PLAYER);
        kalahRule.exert(new KalahContext(kalah, lastSownPit));
        //Then
        assertThat(kalah.getCurrentPlayer()).isEqualTo(FIRST_PLAYER);
        assertThat(kalah.getOtherPlayer()).isEqualTo(SECOND_PLAYER);
    }

    @Test
    void should_set_next_player_when_the_last_sown_pit_is_house_and_belongs_to_current_player(){
        //Given
        var lastSownPit = kalah.getBoard().findPitHavingIndex(5);
        //When
        assertThat(kalah.getCurrentPlayer()).isEqualTo(FIRST_PLAYER);
        assertThat(kalah.getOtherPlayer()).isEqualTo(SECOND_PLAYER);
        kalahRule.exert(new KalahContext(kalah, lastSownPit));
        //Then
        assertThat(kalah.getCurrentPlayer()).isEqualTo(SECOND_PLAYER);
        assertThat(kalah.getOtherPlayer()).isEqualTo(FIRST_PLAYER);
    }
}
