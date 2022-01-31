package com.cycleon.kalah.service;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.cycleon.kalah.TestBase;
import com.cycleon.kalah.domain.model.*;
import com.cycleon.kalah.domain.repo.KalahRepo;
import com.cycleon.kalah.rule.KalahRuleDelegate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class KalahServiceTest extends TestBase {

    @Mock
    private KalahRepo kalahRepo;

    @Mock
    private KalahRuleDelegate ruleDelegate;

    @InjectMocks
    private KalahServiceImpl kalahService;

    @BeforeEach
    void init() {
        super.setUp();
    }

    @Test
    void should_init_kalah() {
        when(kalahRepo.create(isA(Player.class), isA(Player.class))).thenReturn(Mono.just(kalah));
        StepVerifier.create(kalahService.initKalah(FIRST_PLAYER, SECOND_PLAYER)).expectNext(kalah);
    }

    @Test
    void should_select_house() {
        when(kalahRepo.get(kalah.getSessionId())).thenReturn(Mono.just(kalah));
        var house = new Pit(List.of(new Seed(), new Seed()), 3, SECOND_PLAYER, false);
        StepVerifier.create(kalahService.selectHouse(kalah.getSessionId(), house)).assertNext(kalah -> {
            assertThat(kalah.getSessionId()).isEqualTo(kalah.getSessionId());
            assertThat(kalah.getCurrentPlayer()).isEqualTo(FIRST_PLAYER);
            assertThat(kalah.getOtherPlayer()).isEqualTo(SECOND_PLAYER);
        }).verifyComplete();
        verify(ruleDelegate).exert(isA(KalahContext.class));
        verify(kalahRepo).update(isA(Kalah.class));
    }
}
