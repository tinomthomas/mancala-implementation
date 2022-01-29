package com.cycleon.kalah.controller.model;

import com.cycleon.kalah.domain.model.Pit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectHouseRequest {

    @Schema(description = "The kalah session id.", example = "kjhaskd67asdkjh-asdl")
    private String sessionId;

    @Schema(description = "Selected house.", implementation = Pit.class)
    private Pit house;
}
