package com.example.rxjavademo.rx_bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sahil on 18/12/16.
 */

public class RxBusChildFragment1 extends Fragment{

    public static RxBusChildFragment1 newInstance() {

        Bundle args = new Bundle();

        RxBusChildFragment1 fragment = new RxBusChildFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxbus_childfragment1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button1)
    public void onButtonClick(){
        RxBus.getInstance().send("Tap");
    }
}
