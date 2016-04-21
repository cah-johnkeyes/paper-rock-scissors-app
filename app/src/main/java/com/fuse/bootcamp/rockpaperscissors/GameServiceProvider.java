package com.fuse.bootcamp.rockpaperscissors;

import retrofit2.Retrofit;

public class GameServiceProvider {
    public static GameService get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("54.186.48.44:8080")
                .build();

        return retrofit.create(GameService.class);
    }
}
