package com.cycleon.kalah.rule;

import static org.assertj.core.api.Assertions.assertThat;

import com.cycleon.kalah.TestBase;
import com.cycleon.kalah.service.KalahContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

class GameEndTest extends TestBase {

    @BeforeEach
    void init() {
        super.setUp();
        kalahRule = new GameEndRule();
    }

    @Test
    void should_set_the_winner_when_game_ends() {
        //Given
        var lastSownPit = kalah.getBoard().findPitHavingIndex(13);
        lastSownPit.setSeeds(List.of());
        IntStream.range(8, 13).forEach(index -> kalah.getBoard().findPitHavingIndex(index).setSeeds(List.of()));
        //When
        kalahRule.exert(new KalahContext(kalah, lastSownPit));
        //Then
        assertThat(kalah.getBoard().findFirstPlayerStore().getSeeds()).hasSize(24);
        assertThat(kalah.getBoard().findSecondPlayerStore().getSeeds()).isEmpty();
        assertThat(kalah.getWinner()).isEqualTo(FIRST_PLAYER);
    }
}
