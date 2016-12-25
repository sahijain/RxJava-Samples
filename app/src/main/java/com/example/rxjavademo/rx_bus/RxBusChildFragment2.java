package com.example.rxjavademo.rx_bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rxjavademo.R;

import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sahil on 18/12/16.
 */

public class RxBusChildFragment2 extends Fragment{

    public static RxBusChildFragment2 newInstance() {

        Bundle args = new Bundle();

        RxBusChildFragment2 fragment = new RxBusChildFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxbus_childfragment2, container, false);

        subscribe();
        return view;
    }

    private void subscribe(){
        Subscription s = RxBus.getInstance().asObserveable()
                .subscribe(tap -> Toast.makeText(getContext(), "" + tap, Toast.LENGTH_SHORT).show());
        mSubscriptions.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
    }
}
