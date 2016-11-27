package com.example.rxjavademo.concurrency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

        View view = inflater.inflate(R.layout.fragment_concurrency_with_scheduler_demo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button1)
    public void performLongRunningTask(){
        Log.d("Sahil"," Button Clicked");
        Observable.just("sfsf")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just("");
                    }
                })
                /*.map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //Long Running Task
                        Log.d("Sahil"," Within Observables");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return s;
                    }
                })*/
        /*Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //Long Running Task
                Log.d("Sahil"," Within Observables");
                subscriber.onNext("ad");
                subscriber.onCompleted();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })*/.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Sahil"," onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Sahil"," onError()");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("Sahil"," onNext()");
                    }
                });
    }
}
