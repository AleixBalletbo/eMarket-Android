package com.aleixballetbo.emarket;


import android.os.Looper;

import com.aleixballetbo.executor.PostExecutionThread;

import android.os.Handler;

public class UIThread implements PostExecutionThread {

    private final Handler handler;

    public UIThread () {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
