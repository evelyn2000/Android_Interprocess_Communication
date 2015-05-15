package com.example.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.example.data.IRemoteService;

public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("RemoteService", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("RemoteService", "onDestroy");
    }

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("RemoteService", "onBind");
        return mBinder;
    }

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        public void execute(String data){
            Log.d("RemoteService", "execute");
        }
    };
}
