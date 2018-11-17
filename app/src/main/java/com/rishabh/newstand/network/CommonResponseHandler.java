package com.rishabh.newstand.network;

/**
 * Created by appinventiv on 27/3/18.
 */

/**
 * This is to be used for handling common responses
 * such as no network or authentication failed
 * */

public interface CommonResponseHandler {
    void onNetworkError();
}
