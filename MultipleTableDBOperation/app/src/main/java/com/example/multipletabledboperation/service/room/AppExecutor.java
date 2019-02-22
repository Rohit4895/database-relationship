package com.example.multipletabledboperation.service.room;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutor {
    private final Executor loggerIO;
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    public AppExecutor(Executor loggerIO, Executor diskIO, Executor networkIO, Executor mainThread) {
        this.loggerIO = loggerIO;
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    private static AppExecutor INSTANCE = null;

    public static AppExecutor getInstance(){
        if (INSTANCE == null){
            INSTANCE = new AppExecutor(Executors.newFixedThreadPool(1),
                    Executors.newFixedThreadPool(2), Executors.newFixedThreadPool(1),
                    new MainThreadExecutor());
        }
        return INSTANCE;
    }
    private static class MainThreadExecutor implements Executor{
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    }

    public Executor getDiskIO(){
        return diskIO;
    }

    public Executor getLoggerIO(){
        return loggerIO;
    }

    public Executor getNetworkIO(){
        return networkIO;
    }

    public Executor getMainThread(){
        return mainThread;
    }
}
