package com.g7.gibaa007.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by gibaa007 on 17/5/18.
 */

public class BackgroundThread implements Runnable {
    private CountDownLatch latch = new CountDownLatch(1);
    private Handler handler;

    BackgroundThread() {
        Thread thread = new Thread(this);
        thread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            /// e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Looper.prepare();
        handler = new Handler();
        latch.countDown();
        Looper.loop();
    }

    public Handler getHandler() {
        return handler;
    }
}
