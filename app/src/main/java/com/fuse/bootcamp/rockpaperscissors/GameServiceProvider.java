package com.fuse.bootcamp.rockpaperscissors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameServiceProvider {
    public static GameService get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.186.48.44:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GameService.class);
    }
}
