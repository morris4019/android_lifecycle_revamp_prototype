package com.meng.androidlifecyclerevampprototype;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;

/**
 * ActivityB is to showcase the "common textview".
 */
public class ActivityB extends BaseActivity {

    @BindView(R.id.common_textview)
    TextView commonTextView;

    @Override
    public int onRequestContentView(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRequestContentView(savedInstanceState, persistentState);
        return R.layout.activity_b;
    }

    @Override
    public void onPostBindCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostBindCreate(savedInstanceState, persistentState);
        commonTextView.setText("Activity B modified!");
    }
}
