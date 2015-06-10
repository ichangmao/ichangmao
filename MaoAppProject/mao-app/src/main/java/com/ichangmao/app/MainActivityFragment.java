package com.ichangmao.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ichangmao.commons.MaoLog;


public class MainActivityFragment extends Fragment {
    MaoLog log = MaoLog.getLoger(this.getClass());

    TextView tvInfo;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tvInfo = (TextView) rootView.findViewById(R.id.tv_info);
        return rootView;
    }
}
