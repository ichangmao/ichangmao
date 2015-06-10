package com.ichangmao.app;

import android.app.Activity;
import android.os.Bundle;

import com.ichangmao.commons.MaoLog;


public class MainActivity extends Activity {
    MaoLog log = MaoLog.getLoger(this.getClass());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
