package com.fuse.bootcamp.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BeginGamePromptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_game_prompt);
    }

    public void onJoinGame(View view) {
        EditText gameCodeEditText = (EditText) findViewById(R.id.begin_game_prompt_game_code_edit_text);
        String gameCode = gameCodeEditText.getText().toString();

        // TODO: join game using game code
    }

    public void onCreateGame(View view) {
        // TODO: create game and display game code
    }
}
