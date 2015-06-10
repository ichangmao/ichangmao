package com.ichangmao.commons;

import android.util.Log;

public class MaoLog {
    private final String mTag;

    private MaoLog(String tag) {
        mTag = tag;
    }

    public static MaoLog getLoger(Class clazz) {
        return new MaoLog(clazz.getName());
    }

    public void d(String msg) {
        Log.d(mTag, msg);
    }

    public void i(String msg) {
        Log.i(mTag, msg);
    }

    public void w(String msg) {
        Log.w(mTag, msg);
    }

    public void e(String msg) {
        Log.e(mTag, msg);
    }

    public void e(Exception e) {
        e.printStackTrace();
    }
}
