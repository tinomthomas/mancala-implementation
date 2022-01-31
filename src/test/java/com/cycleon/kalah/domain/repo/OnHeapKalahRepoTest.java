package com.cycleon.kalah.domain.repo;

import com.cycleon.kalah.TestBase;
import com.cycleon.kalah.domain.model.Kalah;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

class OnHeapKalahRepoTest extends TestBase {

    private final OnHeapKalahRepo repo = new OnHeapKalahRepo(BOARD_CONFIG);

    @BeforeEach
    void init() {
        super.setUp();
    }

    @Test
    void should_create_new_kalah() {
        StepVerifier.create(repo.create(FIRST_PLAYER, SECOND_PLAYER)).assertNext(this::assertKalah).verifyComplete();
    }

    private void assertKalah(Kalah kalah) {
        assertThat(kalah.getSessionId()).isNotNull();
        assertThat(kalah.getCurrentPlayer()).isEqualTo(FIRST_PLAYER);
        assertThat(kalah.getOtherPlayer()).isEqualTo(SECOND_PLAYER);
        assertThat(kalah.getBoard()).isNotNull();
        assertThat(kalah.getBoard().getPits()).hasSize(14);
        assertThat(kalah.getWinner()).isNull();
    }

    @Test
    void should_get_kalah_for_the_given_session_id() {
        StepVerifier.create(repo.create(FIRST_PLAYER, SECOND_PLAYER))
            .consumeNextWith(kalah -> StepVerifier.create(repo.get(kalah.getSessionId()))
                .assertNext(this::assertKalah).verifyComplete()).verifyComplete();
    }

    @Test
    void should_update_kalah_for_the_given_session_id() {
        StepVerifier.create(
            repo.create(FIRST_PLAYER, SECOND_PLAYER).doOnSuccess(kalah -> {
                assertThat(kalah.getWinner()).isNull();
                kalah.setWinner(SECOND_PLAYER);
            }).flatMap(repo::update).flatMap(newKalah -> repo.get(newKalah.getSessionId())))
            .assertNext(kalah -> assertThat(kalah.getWinner()).isEqualTo(SECOND_PLAYER))
            .verifyComplete();
    }
}
