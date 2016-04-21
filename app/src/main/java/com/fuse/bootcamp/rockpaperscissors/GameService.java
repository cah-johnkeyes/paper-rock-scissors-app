package com.fuse.bootcamp.rockpaperscissors;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GameService {

    @POST("/player")
    Call<Player> submitPlayer(@Body Player player);

    @POST("/game/{gameId}/player/{username}")
    Call<Void> joinGame(@Path("gameId") String gameId, @Path("username") String username);

    @POST("/game/{gameId}/player/{username}/message")
    Call<Void> sendMessage(@Path("gameId") String gameId, @Path("username") String username, @Body String message);
}
