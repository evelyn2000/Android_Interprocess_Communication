package com.example.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("RemoteService", "onCreate");
    }

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        public void execute(String data){
            Log.d("RemoteService", "execute");
        }
    };
}
