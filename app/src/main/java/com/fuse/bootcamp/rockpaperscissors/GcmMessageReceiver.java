package com.fuse.bootcamp.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

public class GcmMessageReceiver extends GcmListenerService {

    public static final String GCM_MESSAGE_BROADCAST_INTENT = "com.fuse.bootcamp.rockpaperscissors.GCM_MESSAGE_BROADCAST_INTENT";
    public static final String GCM_MESSAGE_EXTRA = "com.fuse.bootcamp.rockpaperscissors.GCM_MESSAGE_EXTRA";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.i("GcmMessage", "RECEIVED MESSAGE FROM: " + from);
        Log.i("GcmMessage", "DATA Game.id: " + data.get("id"));

        String messages = (String) data.getSerializable("messages");
        Log.i("GcmMessage", "DATA Message: " + messages);

        Intent intent = new Intent(GCM_MESSAGE_BROADCAST_INTENT);
        intent.putExtra(GCM_MESSAGE_EXTRA, messages);

        sendBroadcast(intent);

        super.onMessageReceived(from, data);
    }
}
