package com.example.rxjavademo.buffer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rxjavademo.R;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewClickEvent;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;

/**
 * Created by sahil on 28/11/16.
 */

public class BufferDemoFragment extends Fragment{

    public static final String TAG = BufferDemoFragment.class.getSimpleName();

    public static BufferDemoFragment newInstance() {

        Bundle args = new Bundle();

        BufferDemoFragment fragment = new BufferDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.buffer)
    public Button mBufferButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buffer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.buffer)
    public void bufferDemo(){
        Observable<ViewClickEvent>  clickEventObservable = RxView.clickEvents(mBufferButton);
        clickEventObservable.buffer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<List<ViewClickEvent>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ViewClickEvent> viewClickEvents) {
                        if(viewClickEvents.size() > 0){
                            Log.d("Sahil"," No of clicks = " + viewClickEvents.size());
                        }

                    }
                });
    }

}
