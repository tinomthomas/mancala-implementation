package com.cycleon.kalah.controller;

import com.cycleon.kalah.controller.model.SelectHouseRequest;
import com.cycleon.kalah.domain.model.Kalah;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface KalahAPI {

    @Operation(description = "Initializes a new Kalah game.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful response with a new Game session",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Kalah.class)))
        }
    )
    @PostMapping(path = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<Kalah> init();

    @Operation(description = "Select the house and apply the rules to other houses",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful response with the modified Game session",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Kalah.class)))
        }
    )
    @PutMapping(path = "/select-house", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    Mono<Kalah> selectHouse(@RequestBody SelectHouseRequest request);
}
