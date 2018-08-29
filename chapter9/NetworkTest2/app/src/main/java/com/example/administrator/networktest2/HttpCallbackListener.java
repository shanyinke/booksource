package com.example.administrator.networktest2;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
