package com.fuse.bootcamp.rockpaperscissors;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GameService {

    @POST("/player")
    Call<Player> submitPlayer(@Body Player player);
}
