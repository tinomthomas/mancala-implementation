package com.cycleon.kalah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cycleon.kalah.configuration")
public class KalahApplication {

    public static void main(String[] args) {
        SpringApplication.run(KalahApplication.class, args);
    }

}
