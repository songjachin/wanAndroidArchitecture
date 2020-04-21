package com.songjachin.wanandroid.application;

import com.billy.cc.core.component.CC;
import com.kingja.loadsir.core.LoadSir;
import com.songjachin.base.BaseApplication;
import com.songjachin.base.loadsir.CustomCallback;
import com.songjachin.base.loadsir.EmptyCallback;
import com.songjachin.base.loadsir.ErrorCallback;
import com.songjachin.base.loadsir.LoadingCallback;
import com.songjachin.base.loadsir.TimeoutCallback;
import com.songjachin.wanandroid.BuildConfig;

public class WanApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setsDebug(BuildConfig.DEBUG);
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();

        CC.enableDebug(BuildConfig.DEBUG);
        CC.enableVerboseLog(BuildConfig.DEBUG);
        CC.enableRemoteCC(BuildConfig.DEBUG);
    }
}
