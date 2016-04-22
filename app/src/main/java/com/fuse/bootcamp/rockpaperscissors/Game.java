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

    public String getOpponentUsername() {
        String playerUsername = GameSession.player.getUsername();
        String opponentUsername = "Opponent";
        for(Player p : players) {
            if (!playerUsername.equals(p.getUsername())) {
                opponentUsername = p.getUsername();
                break;
            }
        }

        return opponentUsername;
    }
}
