package com.meng.androidlifecyclerevampprototype;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

public class BaseActivity extends BaseButterKnifeBoundActivity implements GranularLifecycle.VerboseLoggable {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.button1)
    Button button1;

    @BindView(R.id.common_textview)
    TextView commonTextView;

    @Nullable
    @BindView(R.id.activity_a_textview)
    TextView activityATextView;

    @Override
    protected final void internalInflateCommonElements(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.internalInflateCommonElements(savedInstanceState, persistentState);
        // Not needed for my test but useful
    }

    @Override
    protected final void internalFinalizeOnCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.internalFinalizeOnCreate(savedInstanceState, persistentState);
        // Not needed for my test but useful
    }

    @Override
    public int onRequestContentView(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRequestContentView(savedInstanceState, persistentState);
        return 0;
    }

    @Override
    public void onPreBindCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPreBindCreate(savedInstanceState, persistentState);
        // Setup of things that don't require a bound view
    }

    @Override
    public void onPostBindCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostBindCreate(savedInstanceState, persistentState);
        setSupportActionBar(toolbar);
        setupCommonUi();
        conditionalSetup();
        setupOneTimeTextChange();
    }

    private void conditionalSetup() {
        verboseLogging("conditionalSetup called. activityATextView[" + activityATextView + "]. Attempting to append text...");
        if (activityATextView != null) {
            // To show you that i COULD also modify it here too
            activityATextView.append(", modified by BaseActivity");
        } else {
            verboseLogging(" .. could not append text!");
        }
        verboseLogging(" .. done.");
    }

    private void setupCommonUi() {
        verboseLogging("setupCommonUi called. Setting up listener and modifying commonTextView...");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseActivity.this, "Button 1 click", Toast.LENGTH_SHORT).show();
            }
        });
        commonTextView.setText("Starts from a modification here...");
        verboseLogging(" .. done.");
    }

    private void setupOneTimeTextChange() {
        if (activityATextView != null) {
            activityATextView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    activityATextView.setText("BaseActivity can also modify this!");
                }
            }, 10000);
        }
    }

}
