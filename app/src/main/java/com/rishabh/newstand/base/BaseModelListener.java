package com.rishabh.newstand.base;

import com.rishabh.newstand.pojo.FailureResponse;

/**
 * Created by appinventiv on 23/1/18.
 */

public interface BaseModelListener {
    void noNetworkError();
    void onErrorOccurred(FailureResponse failureResponse);
}