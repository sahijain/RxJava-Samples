package com.example.rxjavademo.polling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rxjavademo.R;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sahil on 8/12/16.
 */

public class PollingDemoFragment extends Fragment {

    public static final String TAG = PollingDemoFragment.class.getSimpleName();

    public static PollingDemoFragment newInstance() {

        Bundle args = new Bundle();

        PollingDemoFragment fragment = new PollingDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_polling, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.timer_task)
    public void timerTask(){
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.io())
                .map(attempt -> {
                    Log.d("Sahil"," Event Fired");
                    return attempt;
                })
                .map(attempt -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return String.valueOf(attempt);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(attemp -> Toast.makeText(getContext(), "Attempt = " + attemp, Toast.LENGTH_SHORT).show());

    }

    @OnClick(R.id.simple_polling)
    public void simplePolling(){

        Subscription s = Observable.interval(0, 10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .map(attempt -> {
                    Log.d("Sahil"," Event fired for polling");
                    return attempt;
                })
                .map(attempt -> doLongRunningOperation(attempt))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(attempt -> Log.d("Sahil"," Polling result Recieved"));

        mSubscriptions.add(s);
    }

    private String doLongRunningOperation(long attempt){
        Log.d("Sahil"," Started Long Running Task");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Sahil"," Finished Long Running Task");
        return String.valueOf(attempt);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
    }
}
