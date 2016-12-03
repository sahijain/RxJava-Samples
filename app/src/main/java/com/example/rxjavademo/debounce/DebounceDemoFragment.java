package com.example.rxjavademo.debounce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rxjavademo.R;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sahil on 3/12/16.
 */

public class DebounceDemoFragment extends Fragment{

    public static final String TAG = DebounceDemoFragment.class.getSimpleName();

    public static DebounceDemoFragment newInstance() {

        Bundle args = new Bundle();

        DebounceDemoFragment fragment = new DebounceDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.debounce_edit_text)
    AppCompatEditText mEditText;

    private CompositeSubscription mTextChangeSubscriptions = new CompositeSubscription();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_debounce, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    private void init(){
        RxTextView.textChanges(mEditText).skip(1)
                .map(CharSequence -> {
                    Log.d(TAG, "Event Fired");
                    return CharSequence;
                })
                .debounce(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .filter(charSequence -> TextUtils.isEmpty(charSequence))
                .subscribe(charSequence ->{
                    onTextChangeEvent(charSequence.toString());
                }, throwable -> {
                    Log.wtf(TAG, "init: ", throwable);
                });
    }

    private void onTextChangeEvent(String inputText){
        mTextChangeSubscriptions.clear();
        Log.d(TAG, "onTextChangeEvent(),input = " + inputText);
    }
}
