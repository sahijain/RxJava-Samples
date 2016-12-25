package com.example.rxjavademo.twoway_data_binding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rxjavademo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import rx.subjects.PublishSubject;

/**
 * Created by sahil on 7/12/16.
 */

public class PublishSubjectDemoFragment extends Fragment{

    public static final String TAG = PublishSubjectDemoFragment.class.getSimpleName();

    public static PublishSubjectDemoFragment newInstance() {

        Bundle args = new Bundle();

        PublishSubjectDemoFragment fragment = new PublishSubjectDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.result)
    TextView mResultTextView;
    @BindView(R.id.edit_text1)
    EditText mEditText1;
    @BindView(R.id.edit_text2)
    EditText mEditText2;

    PublishSubject<Integer> mResultEmitter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_publish_subject, container, false);
        ButterKnife.bind(this, view);

        mResultEmitter = PublishSubject.create();
        mResultEmitter.asObservable()
                .subscribe(result -> {
                    mResultTextView.setText(String.valueOf(result));
                }, throwable -> throwable.printStackTrace());

        return view;
    }

    @OnTextChanged({R.id.edit_text1, R.id.edit_text2})
    public void onTextChange(){
        int num1 = TextUtils.isEmpty(mEditText1.getText().toString()) ? 0 : Integer.parseInt(mEditText1.getText().toString());
        int num2 = TextUtils.isEmpty(mEditText2.getText().toString()) ? 0 : Integer.parseInt(mEditText2.getText().toString());

        mResultEmitter.onNext(num1 + num2);
    }
}
