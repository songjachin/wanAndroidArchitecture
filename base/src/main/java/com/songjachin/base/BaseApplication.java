package com.songjachin.base;

import android.app.Application;

public class BaseApplication extends Application {
    public static Application sApplication;
    public static boolean sDebug;
    public void setsDebug(boolean isDebug){
        sDebug = isDebug;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
