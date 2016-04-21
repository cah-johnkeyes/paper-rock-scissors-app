package com.fuse.bootcamp.rockpaperscissors;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GameService {

    @POST("player")
    Player submitPlayer(@Body Player player);
}
