package com.fuse.bootcamp.rockpaperscissors;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

public class GameMessageReceiver extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.i("GameMessage", "RECEIVED MESSAGE FROM: " + from);
        Log.i("GameMessage", "DATA Game.id: " + data.get("id"));

        super.onMessageReceived(from, data);
    }
}
