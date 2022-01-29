package com.cycleon.kalah.configuration;

import com.cycleon.kalah.controller.KalahAPI;
import com.cycleon.kalah.controller.KalahController;
import com.cycleon.kalah.domain.repo.OnHeapKalahRepo;
import com.cycleon.kalah.rule.*;
import com.cycleon.kalah.service.KalahService;
import com.cycleon.kalah.service.KalahServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@EnableConfigurationProperties(BoardConfiguration.class)
public class KalahConfiguration {

    @Bean
    public KalahService kalahService(BoardConfiguration settings, KalahRuleDelegate ruleDelegate) {
        return new KalahServiceImpl(new OnHeapKalahRepo(settings), ruleDelegate);
    }

    @Bean
    public KalahRuleDelegate kalahRuleDelegate() {
        return new KalahRuleDelegate(List.of(
            new FirstSelectionOnEmptyPitRule(), new SeedSowRule(), new LastSowToEmptyPitRule(),
            new SetNextPlayerRule(), new GameEndRule())
        );
    }

    @Bean
    public KalahAPI kalahAPI(KalahService kalahService) {
        return new KalahController(kalahService);
    }
}
