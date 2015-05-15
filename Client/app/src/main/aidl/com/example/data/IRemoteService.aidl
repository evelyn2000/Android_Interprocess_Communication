// IRemoteService.aidl
package com.example.data;

import com.example.data.IRemoteServiceCallback;
// Declare any non-default types here with import statements

oneway interface IRemoteService {
    void execute(String data, IRemoteServiceCallback callback);
}