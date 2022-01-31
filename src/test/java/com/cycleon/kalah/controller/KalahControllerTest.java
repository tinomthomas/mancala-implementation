package com.cycleon.kalah.controller;

import com.cycleon.kalah.TestBase;
import com.cycleon.kalah.controller.model.SelectHouseRequest;
import com.cycleon.kalah.domain.model.Pit;
import com.cycleon.kalah.service.KalahService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KalahControllerTest extends TestBase {

    @Mock
    private KalahService kalahService;

    @InjectMocks
    private KalahController kalahController;

    @BeforeEach
    void init() {
        super.setUp();
    }

    @Test
    void should_init_kalah(){
        when(kalahService.initKalah(FIRST_PLAYER, SECOND_PLAYER)).thenReturn(Mono.just(kalah));
        StepVerifier.create(kalahController.init()).expectNext(kalah);
    }

    @Test
    void should_select_house() {
        when(kalahService.selectHouse(isA(String.class), isA(Pit.class))).thenReturn(Mono.just(kalah));
        StepVerifier.create(kalahController
            .selectHouse(new SelectHouseRequest(kalah.getSessionId(), kalah.getBoard().findPitHavingIndex(3))))
            .expectNext(kalah);
    }

}
