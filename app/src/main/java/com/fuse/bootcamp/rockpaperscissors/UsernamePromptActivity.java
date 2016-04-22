package com.fuse.bootcamp.rockpaperscissors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsernamePromptActivity extends AppCompatActivity {

    private static final String USERNAME_SHARED_PREFS_KEY = "com.fuse.bootcamp.rockpaperscissors.USERNAME_SHARED_PREFS_KEY";

    private SharedPreferences sharedPrefs;
    private GameService gameService;
    private String gcmToken;

    private BroadcastReceiver gcmBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            gcmToken = intent.getStringExtra(GcmRegistrationService.GCM_TOKEN_EXTRAS);
            if (gcmToken != null && !gcmToken.isEmpty()) {
                init();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerReceiver(gcmBroadcastReceiver, new IntentFilter(GcmRegistrationService.GCM_TOKEN_RECEIVED_BROADCAST_INTENT));

        Intent i = new Intent(this, GcmRegistrationService.class);
        startService(i);

        gameService = GameServiceProvider.get();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(gcmBroadcastReceiver);
    }

    public void onSubmitUsername(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.username_prompt_edit_text);
        final String username = usernameEditText.getText().toString();

        if (!username.isEmpty()) {
            final Player player = new Player(username, gcmToken);
            gameService.submitPlayer(player).enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString(USERNAME_SHARED_PREFS_KEY, username);
                    editor.apply();

                    GameSession.player = player;
                    promptToStartOrJoinGame();
                }

                @Override
                public void onFailure(Call<Player> call, Throwable t) {
                    throw new RuntimeException("Could not initialize player!");
                }
            });
        }
    }

    private void init() {
        sharedPrefs = getPreferences(MODE_PRIVATE);
        String username = sharedPrefs.getString(USERNAME_SHARED_PREFS_KEY, "");

        if (!username.isEmpty()) {
            GameSession.player = new Player(username, gcmToken);
            promptToStartOrJoinGame();
        } else {
            setContentView(R.layout.activity_username_prompt);
        }
    }

    private void promptToStartOrJoinGame() {
        Intent intent = new Intent(this, BeginGamePromptActivity.class);
        startActivity(intent);
    }
}
