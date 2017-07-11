package com.sunnyxibei.gogank;

import android.app.Application;
import android.content.Context;

import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by jiayuanbin on 2016/8/7.
 */

public class SunnyApplication extends Application {

    private static SunnyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        TastyToast.makeText(this, "Go Gank !",
                TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }

    public static Context getContext() {
        return context;
    }
}
