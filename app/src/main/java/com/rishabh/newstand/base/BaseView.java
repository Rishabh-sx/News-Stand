package com.rishabh.newstand.base;

import com.rishabh.newstand.pojo.FailureResponse;


public interface BaseView {

    void showNoNetworkError();
    void showToastLong(String message);
    void showSpecificError(FailureResponse failureResponse);
    void showProgressDialog();
    void hideProgressDialog();
}
