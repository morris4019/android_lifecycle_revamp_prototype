package com.meng.androidlifecyclerevampprototype;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;

/**
 * ActivityA is a small showcase of an activity, in the inheritance chain, which has both a common
 * and unique UI element.
 */
public class ActivityA extends BaseActivity{

    // "Common" here refers to us specifically using it in multiple views-it's just a name.
    @BindView(R.id.common_textview)
    TextView commonTextView;

    // This textview is ONLY within the ActivityA layout.
    @BindView(R.id.activity_a_textview)
    TextView activityATextView;

    // Used to link to ActivityB.
    @BindView(R.id.button2)
    Button button2;

    @Override
    public int onRequestContentView(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRequestContentView(savedInstanceState, persistentState);
        return R.layout.activity_a;
    }

    @Override
    public void onPreBindCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPreBindCreate(savedInstanceState, persistentState);
        // No-op
    }

    @Override
    public void onPostBindCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostBindCreate(savedInstanceState, persistentState);
        activityATextView.setText("Activity A can modify this.");
        commonTextView.setText("Ends up being this ;)");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent activityBIntent = new Intent(ActivityA.this, ActivityB.class);
                startActivity(activityBIntent);
            }
        });
    }
}
