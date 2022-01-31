package com.cycleon.kalah.controller.model;

import com.cycleon.kalah.domain.model.Pit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class SelectHouseRequest {

    @Schema(description = "The kalah session id.", example = "kjhaskd67asdkjh-asdl")
    String sessionId;

    @Schema(description = "Selected house.", implementation = Pit.class)
    Pit house;
}
