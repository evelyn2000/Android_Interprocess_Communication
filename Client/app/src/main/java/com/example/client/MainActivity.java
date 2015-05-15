package com.example.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.data.IRemoteService;
import com.example.data.IRemoteServiceCallback;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.e("ClassName", MainActivity.class.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mIRemoteService != null){
            unbindService(mConnection);
        }
    }

    IRemoteServiceCallback.Stub mCallback = new IRemoteServiceCallback.Stub() {
        public void handleResponse(String name) throws RemoteException {
            Log.d("Callback", name);
        }
    };
    // Bind RemoteService
    IRemoteService mIRemoteService;
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            Log.e("Client", "onServiceConnected");
            mIRemoteService = IRemoteService.Stub.asInterface(service);

            try{
                IRemoteServiceCallback callback = IRemoteServiceCallback.Stub.asInterface(mCallback);
                String s = "hello from Client";
                mIRemoteService.execute(s, callback);
            }catch (RemoteException e){
                e.printStackTrace();
            }


        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.e("Error", "Service has unexpectedly disconnected");
            mIRemoteService = null;
        }
    };


    public void initConnection(){
        Log.e("ClassName", "initConnection");


        // IMPORTANT!
        Intent intent = new Intent("com.example.data.RemoteService"); // Intent-filter in Server Project
        intent.setPackage("com.example.server"); // Server project package name

        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void sendRequest(View v){
        Toast.makeText(this, "sendRequest", Toast.LENGTH_SHORT).show();
        initConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
