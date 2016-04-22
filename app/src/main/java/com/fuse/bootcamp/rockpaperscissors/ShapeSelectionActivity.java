package com.fuse.bootcamp.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShapeSelectionActivity extends AppCompatActivity {

    private GameService gameService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameService = GameServiceProvider.get();
        setContentView(R.layout.activity_shape_selection);
    }

    public void onShapeSelected(View view) {
        String rockTag = getString(R.string.shape_rock);
        String paperTag = getString(R.string.shape_paper);

        String shapeSelection = view.getTag().toString();
        Shape shape;

        if (rockTag.equals(shapeSelection)) {
            shape = Shape.ROCK;
        } else if (paperTag.equals(shapeSelection)) {
            shape = Shape.PAPER;
        } else {
            shape = Shape.SCISSORS;
        }

        String gameId = String.valueOf(GameSession.game.getId());
        String username = GameSession.player.getUsername();
        gameService.sendMessage(gameId, username, shape.toString()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                showResultsScreen();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ShapeSelectionActivity.this, "Failed to send message. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showResultsScreen() {
        Intent intent = new Intent(this, GameResultActivity.class);
        startActivity(intent);
    }
}
