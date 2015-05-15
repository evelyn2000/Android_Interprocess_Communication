// IRemoteService.aidl
package com.example.data;

// Declare any non-default types here with import statements

interface IRemoteServiceCallback {
    void handleResponse(String data);
}