package com.meng.androidlifecyclerevampprototype;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

public abstract class BaseButterKnifeBoundActivity extends AppCompatActivity implements GranularLifecycle {

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        verboseLogging("onCreate(2) called. calling through to super...");
        super.onCreate(savedInstanceState, persistentState);
        verboseLogging(" .. super returned. executing internal onCreate process...");
        internalOnCreate(savedInstanceState, persistentState);
        verboseLogging(" .. completed full onCreate.");
    }

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        verboseLogging("onCreate(1) called. calling through to super...");
        super.onCreate(savedInstanceState);
        verboseLogging(" .. super returned. executing internal onCreate process...");
        internalOnCreate(savedInstanceState, null);
        verboseLogging(" .. completed full onCreate.");
    }

    protected void internalOnCreate(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState) {

        verboseLogging("internalOnCreate called. requesting content view...");

        setContentView(onRequestContentView(savedInstanceState, persistentState));

        verboseLogging(" .. done. inflating common elements...");

        internalInflateCommonElements(savedInstanceState, persistentState);

        verboseLogging(" .. done. executing all pre bind creation...");

        onPreBindCreate(savedInstanceState, persistentState);

        verboseLogging(" .. done. binding... hold on to your butts!");

        ButterKnife.bind(this);

        verboseLogging(" .. butts held tight enough-it WORKED! executing post bind creation...");

        onPostBindCreate(savedInstanceState, persistentState);

        verboseLogging(" .. done. finalizing onCreate...");

        internalFinalizeOnCreate(savedInstanceState, persistentState);

        verboseLogging(" .. done. finished. out'a'here");

    }

    protected void internalInflateCommonElements(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState) {
        verboseLogging("internalInflateCommonElements called. no-op");
    }

    protected void internalFinalizeOnCreate(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState) {
        verboseLogging("internalFinalizeOnCreate called. no-op");
    }

    @Override
    public int onRequestContentView(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        verboseLogging("onRequestContentView called. returning 0.");
        return 0;
    }

    @Override
    public void onPreBindCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        verboseLogging("onPreBindCreate called. no-op");
    }

    @Override
    public void onPostBindCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        verboseLogging("onPostBindCreate called. no-op");
    }

    protected void verboseLogging(final String str) {
        if (this instanceof GranularLifecycle.VerboseLoggable) {
            final String msg = "[" + getClass().getSimpleName() + "] " + str;
            Log.v("[VerboseLogging]", msg);
        }
    }
}
