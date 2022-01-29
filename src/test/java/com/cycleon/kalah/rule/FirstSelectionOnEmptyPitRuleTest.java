package com.cycleon.kalah.rule;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.cycleon.kalah.domain.model.Pit;
import com.cycleon.kalah.exception.InvalidSelectionException;
import com.cycleon.kalah.service.KalahContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class FirstSelectionOnEmptyPitRuleTest extends RuleTestBase {

    private KalahRule kalahRule;

    @BeforeEach
    void init() {
        this.kalahRule = new FirstSelectionOnEmptyPitRule();
    }

    @Test
    void should_throw_exception_if_selected_pit_is_empty() {
        var kalahContext = new KalahContext(null, new Pit(List.of(), 2, null, false));
        assertThatThrownBy(() -> kalahRule.exert(kalahContext)).isExactlyInstanceOf(InvalidSelectionException.class);
    }
}
