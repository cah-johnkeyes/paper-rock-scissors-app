package com.fuse.bootcamp.rockpaperscissors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameResultActivity extends AppCompatActivity {

    private GameService gameService;

    private BroadcastReceiver opponentResponseBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(GcmMessageReceiver.GCM_MESSAGE_EXTRA);
            displayOpponentMove(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerReceiver(opponentResponseBroadcastReceiver, new IntentFilter(GcmMessageReceiver.GCM_MESSAGE_BROADCAST_INTENT));

        gameService = GameServiceProvider.get();

        setContentView(R.layout.activity_game_result);

        displayPlayerMove();
        getOpponentUsername();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(opponentResponseBroadcastReceiver);
    }

    private void displayPlayerMove() {
        TextView playerMoveTextView = (TextView) findViewById(R.id.game_result_player_move_txt);
        if (playerMoveTextView != null) {
            playerMoveTextView.setText(GameSession.playerMove.toString());
        }
    }

    private void getOpponentUsername() {
        String gameId = String.valueOf(GameSession.game.getId());
        gameService.getGame(gameId).enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                GameSession.game = response.body();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayOpponentUsername();
                    }
                });
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                throw new RuntimeException(t.getMessage());
            }
        });

    }

    private void displayOpponentUsername() {
        TextView opponentMoveHeaderTextView = (TextView) findViewById(R.id.game_result_opponent_move_header_txt);
        if (opponentMoveHeaderTextView != null) {
            String opponentUsername = GameSession.game.getOpponentUsername();
            String opponentMoveHeader = getResources().getString(R.string.game_result_opponent_move_header_format, opponentUsername);
            opponentMoveHeaderTextView.setText(opponentMoveHeader);
        }
    }

    private void displayOpponentMove(String message) {
        TextView opponentMoveTextView = (TextView) findViewById(R.id.game_result_opponent_move_txt);
        if (opponentMoveTextView != null) {
            opponentMoveTextView.setText(message);
        }

        // TODO: display win/lose/tie
    }
}
