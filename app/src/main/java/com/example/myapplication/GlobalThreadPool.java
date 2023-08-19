package com.example.myapplication;

import androidx.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GlobalThreadPool {
    private static ExecutorService mInstance;
    // TODO: look into making an annotation instead of checkInstance
    @NonNull
    public static synchronized ExecutorService getInstance() {
        checkInstance();
        return mInstance;
    }

    public static synchronized Future<?> submit(Runnable r) {
        checkInstance();
        return mInstance.submit(r);
    }

    public static synchronized <T> Future<T> submit(Callable<T> c) {
        checkInstance();
        return mInstance.submit(c);
    }

    private static void checkInstance() {
        if (mInstance == null)
            mInstance = createThreadPool();
    }

    private static ExecutorService createThreadPool() {
        return Executors.newCachedThreadPool();
    }
}
