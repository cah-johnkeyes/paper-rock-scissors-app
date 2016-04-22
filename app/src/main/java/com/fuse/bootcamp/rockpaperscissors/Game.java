package com.fuse.bootcamp.rockpaperscissors;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private int id;

    public Game() {
        players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
