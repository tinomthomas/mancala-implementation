package com.cycleon.kalah.rule;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.cycleon.kalah.TestBase;
import com.cycleon.kalah.domain.model.Pit;
import com.cycleon.kalah.domain.model.Seed;
import com.cycleon.kalah.exception.InvalidSelectionException;
import com.cycleon.kalah.service.KalahContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class FirstSelectionOnEmptyPitTest extends TestBase {

    private KalahRule kalahRule;

    @BeforeEach
    void init() {
        this.kalahRule = new FirstSelectionOnEmptyPitRule();
    }

    @Test
    void should_throw_exception_if_selected_pit_is_empty() {
        var kalahContext = expectKalahContextWith(List.of());
        assertThatThrownBy(() -> kalahRule.exert(kalahContext)).isExactlyInstanceOf(InvalidSelectionException.class);
    }

    @Test
    void should_not_throw_exception_if_selected_pit_is_empty() {
        var kalahContext = expectKalahContextWith(List.of(new Seed()));
        assertThatCode(() -> kalahRule.exert(kalahContext)).doesNotThrowAnyException();
    }

    private KalahContext expectKalahContextWith(List<Seed> seeds) {
        return new KalahContext(null, new Pit(seeds, 2, null, false));
    }
}
