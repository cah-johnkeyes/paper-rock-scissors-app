package com.fuse.bootcamp.rockpaperscissors;

public class Player {
    private String username;
    private String deviceToken;

    public Player(String username, String deviceToken) {
        this.username = username;
        this.deviceToken = deviceToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
