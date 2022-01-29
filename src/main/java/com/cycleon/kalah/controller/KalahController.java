package com.cycleon.kalah.controller;

import com.cycleon.kalah.controller.model.SelectHouseRequest;
import com.cycleon.kalah.domain.model.Kalah;
import com.cycleon.kalah.domain.model.Player;
import com.cycleon.kalah.domain.model.Position;
import com.cycleon.kalah.service.KalahService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class KalahController implements KalahAPI {

    private final KalahService kalahService;

    @Override
    public Mono<Kalah> init() {
        return kalahService.initKalah(
            new Player("John", "Doe", "johndoe", Position.TOP),
            new Player("Alice", "Bob", "alicebob", Position.BOTTOM)
        );
    }

    @Override
    public Mono<Kalah> selectHouse(@RequestBody SelectHouseRequest request) {
        return kalahService.selectHouse(request.getSessionId(), request.getHouse());
    }
}
