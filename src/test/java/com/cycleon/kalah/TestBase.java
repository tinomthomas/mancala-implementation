package com.cycleon.kalah;

import com.cycleon.kalah.configuration.BoardConfiguration;
import com.cycleon.kalah.domain.model.Kalah;
import com.cycleon.kalah.domain.model.Player;
import com.cycleon.kalah.domain.model.Position;
import com.cycleon.kalah.rule.KalahRule;

public class TestBase {

    protected static final Player FIRST_PLAYER = new Player("John", "Doe", "johndoe", Position.TOP);
    protected static final Player SECOND_PLAYER = new Player("Alice", "Bob", "alicebob", Position.BOTTOM);
    protected static final BoardConfiguration BOARD_CONFIG = new BoardConfiguration(new BoardConfiguration.HouseConfig(4, 6));

    protected KalahRule kalahRule;

    protected Kalah kalah;

    public void setUp(){
        kalah = new Kalah(FIRST_PLAYER, SECOND_PLAYER,BOARD_CONFIG);
    }
}
