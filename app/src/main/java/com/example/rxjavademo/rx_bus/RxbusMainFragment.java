package com.example.rxjavademo.rx_bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rxjavademo.R;

/**
 * Created by sahil on 18/12/16.
 */

public class RxbusMainFragment extends Fragment{

    public static RxbusMainFragment newInstance() {

        Bundle args = new Bundle();

        RxbusMainFragment fragment = new RxbusMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_rxbus, container, false);
        init();
        return view;
    }

    private void init(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment1_container, RxBusChildFragment1.newInstance(), "efg")
                .replace(R.id.fragment2_container, RxBusChildFragment2.newInstance(), "wfwef")
                .commit();
    }
}
