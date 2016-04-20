package com.fuse.bootcamp.rockpaperscissors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UsernamePromptActivity extends AppCompatActivity {

    private static final String USERNAME_SHARED_PREFS_KEY = "com.fuse.bootcamp.rockpaperscissors.USERNAME_SHARED_PREFS_KEY";

    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = getPreferences(MODE_PRIVATE);
        String username = sharedPrefs.getString(USERNAME_SHARED_PREFS_KEY, "");

        if (!username.isEmpty()) {
            promptToStartOrJoinGame();
        } else {
            setContentView(R.layout.activity_username_prompt);
        }
    }

    private void promptToStartOrJoinGame() {
        Intent intent = new Intent(this, BeginGamePromptActivity.class);
        startActivity(intent);
    }

    public void onSubmitUsername(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.username_prompt_edit_text);
        String username = usernameEditText.getText().toString();

        if (!username.isEmpty()) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(USERNAME_SHARED_PREFS_KEY, username);
            editor.apply();

            promptToStartOrJoinGame();
        }
    }
}
