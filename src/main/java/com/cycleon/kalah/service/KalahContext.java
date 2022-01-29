package com.cycleon.kalah.service;

import com.cycleon.kalah.domain.model.Kalah;
import com.cycleon.kalah.domain.model.Pit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KalahContext {
    private Kalah kalah;
    private Pit currentPit;
}
