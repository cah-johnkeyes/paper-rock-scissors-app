package com.fuse.bootcamp.rockpaperscissors;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class GcmRegistrationService extends IntentService {

    private static final String[] TOPICS = {"GLOBAL"};

    public GcmRegistrationService() {
        this(GcmRegistrationService.class.getSimpleName());
    }

    public GcmRegistrationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            String token = getToken(this);
            subscribeTopics(this, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getToken(Context context) throws IOException {
        String projectNumber = "915849350756"; // todo: extract to strings.xml
        InstanceID instanceId = InstanceID.getInstance(context);
        String token = instanceId.getToken(projectNumber, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
        Log.i("GCM", "TOKEN: " + token);
        return token;
    }

    private void subscribeTopics(Context context, String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(context);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
