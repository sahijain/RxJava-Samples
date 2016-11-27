package com.example.rxjavademo.concurrency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sahil on 27/11/16.
 */

public class ConcurrencyDemoFragment extends Fragment{

    public static final String TAG = ConcurrencyDemoFragment.class.getSimpleName();

    public static ConcurrencyDemoFragment newInstance() {

        Bundle args = new Bundle();

        ConcurrencyDemoFragment fragment = new ConcurrencyDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
