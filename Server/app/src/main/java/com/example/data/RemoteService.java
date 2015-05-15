package com.example.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.example.data.IRemoteServiceCallback;

public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("RemoteService", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("RemoteService", "onDestroy");
    }

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("RemoteService", "onBind");
        return mBinder;
    }

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        public void execute(String data, IRemoteServiceCallback callback){
            Log.d("RemoteService", "execute " + data);
            try{
                callback.handleResponse("Response from server");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };
}
