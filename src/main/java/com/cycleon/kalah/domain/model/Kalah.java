package com.cycleon.kalah.domain.model;

import com.cycleon.kalah.configuration.BoardConfiguration;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Kalah {
    @NonNull
    private final Board board;
    @NonNull
    private final String sessionId;
    @NonNull
    @Setter
    private Player currentPlayer;
    @NonNull
    @Setter
    private Player otherPlayer;
    @Setter
    private Player winner;

    public Kalah(Player firstPlayer, Player secondPlayer, BoardConfiguration settings) {
        this.board = new Board(firstPlayer, secondPlayer, settings);
        this.sessionId = UUID.randomUUID().toString();
        this.currentPlayer = firstPlayer;
        this.otherPlayer = secondPlayer;
    }


}
