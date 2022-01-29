package com.cycleon.kalah.domain.model;

import com.cycleon.kalah.configuration.BoardConfiguration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class Board {

    List<Pit> pits;
    @JsonIgnore
    BoardSettings settings;

    public Board(Player firstPlayer, Player secondPlayer, BoardConfiguration boardConfiguration) {
        this.settings = new BoardSettings(boardConfiguration);
        this.pits = IntStream.rangeClosed(1, 14)
            .mapToObj(i -> new Pit(withInitialSeeds(i), i, withPlayer(i, firstPlayer, secondPlayer), isStore(i)))
            .collect(Collectors.toList());

    }

    public Pit findPitHavingIndex(int index) {
        return pits.stream().filter(p -> p.getIndex() == index).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public Pit findNextPit(Pit pit) {
        var nextPitIndex = pit.getIndex() + 1;
        if (nextPitIndex > settings.getSecondPlayerStoreIndex()) {
            nextPitIndex = 1;
        }
        return findPitHavingIndex(nextPitIndex);
    }

    public Pit findOppositePitOf(Pit currentPit) {
        var oppositePitIndex = settings.getSecondPlayerStoreIndex() - currentPit.getIndex();
        return findPitHavingIndex(oppositePitIndex);
    }

    public Pit findStoreOf(Player player) {
        return pits.stream().filter(Pit::isStore).filter(pit -> pit.belongsTo(player))
            .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public int findFirstPlayerTotalNoOfSeedsInHouses() {
        return findTotalNoOfSeedsIn(this::firstPlayerHouses);
    }

    public int findSecondPlayerTotalNoOfSeedsInHouses() {
        return findTotalNoOfSeedsIn(this::secondPlayerHouses);
    }

    public Pit findFirstPlayerStore() {
        return findStoreWith(settings.getFirstPlayerStoreIndex());
    }

    public Pit findSecondPlayerStore() {
        return findStoreWith(settings.getSecondPlayerStoreIndex());
    }

    private Pit findStoreWith(int index) {
        return pits.stream().filter(pit -> pit.getIndex() == index).findFirst().orElseThrow();
    }

    public List<Seed> findTotalSeedsInHousesOf(Player player) {
        return pits.stream().filter(Pit::isNotStore).filter(pit -> pit.belongsTo(player))
            .map(Pit::getSeeds).flatMap(List::stream).collect(Collectors.toList());
    }

    private int findTotalNoOfSeedsIn(Predicate<Pit> predicate) {
        return pits.stream().filter(predicate).map(Pit::getSeeds).map(List::size).reduce(0, Integer::sum);
    }

    private boolean firstPlayerHouses(Pit pit) {
        return pit.getIndex() < settings.getFirstPlayerStoreIndex();
    }

    private boolean secondPlayerHouses(Pit pit) {
        return pit.getIndex() > settings.getFirstPlayerStoreIndex() && pit.getIndex() < settings.getSecondPlayerStoreIndex();
    }

    private Player withPlayer(int index, Player firstPlayer, Player secondPlayer) {
        return index <= settings.getFirstPlayerStoreIndex() ? firstPlayer : secondPlayer;
    }

    private List<Seed> withInitialSeeds(int index) {
        return isStore(index) ? new ArrayList<>() : IntStream.range(0, settings.getInitialHouseSeeds())
            .mapToObj(i -> new Seed()).collect(Collectors.toList());
    }

    private boolean isStore(int index) {
        return index == settings.getFirstPlayerStoreIndex() || index == settings.getSecondPlayerStoreIndex();
    }
}
