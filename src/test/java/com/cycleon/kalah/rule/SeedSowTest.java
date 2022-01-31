package com.cycleon.kalah.rule;

import static org.assertj.core.api.Assertions.assertThat;

import com.cycleon.kalah.TestBase;
import com.cycleon.kalah.domain.model.Seed;
import com.cycleon.kalah.service.KalahContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SeedSowTest extends TestBase {

    @BeforeEach
    void init() {
        super.setUp();
        kalahRule = new SeedSowRule();
    }

    @Test
    void should_sow_to_player_houses_and_pit() {
        //Given
        var selectedPit = kalah.getBoard().findPitHavingIndex(3);
        //When
        kalahRule.exert(new KalahContext(kalah, selectedPit));
        //Then
        assertThat(kalah.getBoard().findPitHavingIndex(3).getSeeds()).isEmpty();
        assertThat(kalah.getBoard().findPitHavingIndex(4).getSeeds()).hasSize(5);
        assertThat(kalah.getBoard().findFirstPlayerStore().getSeeds()).hasSize(1);
    }

    @Test
    void should_sow_to_pit_and_other_player_houses() {
        //Given
        var selectedPit = kalah.getBoard().findPitHavingIndex(5);
        //When
        kalahRule.exert(new KalahContext(kalah, selectedPit));
        assertThat(kalah.getBoard().findPitHavingIndex(5).getSeeds()).isEmpty();
        assertThat(kalah.getBoard().findPitHavingIndex(6).getSeeds()).hasSize(5);
        assertThat(kalah.getBoard().findFirstPlayerStore().getSeeds()).hasSize(1);
        assertThat(kalah.getBoard().findPitHavingIndex(8).getSeeds()).hasSize(5);
    }

    @Test
    void should_not_sow_to_other_player_store() {
        //Given
        var selectedPit = kalah.getBoard().findPitHavingIndex(6);
        selectedPit.setSeeds(IntStream.range(0, 8).mapToObj(i -> new Seed()).collect(Collectors.toList()));
        //When
        kalahRule.exert(new KalahContext(kalah, selectedPit));
        //Then
        assertThat(kalah.getBoard().findPitHavingIndex(6).getSeeds()).isEmpty();
        assertThat(kalah.getBoard().findPitHavingIndex(8).getSeeds()).hasSize(5);
        assertThat(kalah.getBoard().findFirstPlayerStore().getSeeds()).hasSize(1);
        assertThat(kalah.getBoard().findPitHavingIndex(1).getSeeds()).hasSize(5);
        assertThat(kalah.getBoard().findSecondPlayerStore().getSeeds()).isEmpty();
    }
}
