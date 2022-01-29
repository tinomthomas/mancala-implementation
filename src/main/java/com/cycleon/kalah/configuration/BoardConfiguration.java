package com.cycleon.kalah.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("board.configuration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardConfiguration {

    @NonNull
    private HouseConfig house = new HouseConfig();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class HouseConfig {
        private int initialSeeds;
        private int count;
    }
}
