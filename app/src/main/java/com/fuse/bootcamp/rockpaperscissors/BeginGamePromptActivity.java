package com.fuse.bootcamp.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeginGamePromptActivity extends AppCompatActivity {

    private GameService gameService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameService = GameServiceProvider.get();
        registerUsername();
        setContentView(R.layout.activity_begin_game_prompt);
    }

    public void onJoinGame(View view) {
        EditText gameCodeEditText = (EditText) findViewById(R.id.begin_game_prompt_game_code_edit_text);
        if (gameCodeEditText != null) {
            String gameId = gameCodeEditText.getText().toString();

            if (!gameId.isEmpty()) {
                String username = GameSession.player.getUsername();
                gameService.joinGame(gameId, username).enqueue(new Callback<Game>() {
                    @Override
                    public void onResponse(Call<Game> call, Response<Game> response) {
                        GameSession.game = response.body();
                        startGame();
                    }

                    @Override
                    public void onFailure(Call<Game> call, Throwable t) {
                        Toast.makeText(BeginGamePromptActivity.this, "Failed to join game. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(BeginGamePromptActivity.this, "Enter a game ID to join a game.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onCreateGame(View view) {
        gameService.createGame().enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                GameSession.game = response.body();
                displayGameId(GameSession.game.getId());
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Toast.makeText(BeginGamePromptActivity.this, "Failed to create game. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayGameId(final int gameId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView gameIdTextView = (TextView) findViewById(R.id.begin_game_prompt_game_id_txt);
                if (gameIdTextView != null) {
                    gameIdTextView.setText(String.valueOf(gameId));
                }
            }
        });
    }

    private void registerUsername() {
        gameService.submitPlayer(GameSession.player).enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                // yay!
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(BeginGamePromptActivity.this, "Could not register username. Try reloading the app", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(this, ShapeSelectionActivity.class);
        startActivity(intent);
    }
}
