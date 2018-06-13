package com.meng.androidlifecyclerevampprototype;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * BaseButterKnifeBoundActivity adds granular onCreate process methods as well as prevent subclasses
 * from further overwriting the, android provided, onCreate methods.
 *
 * Verbose Logging added to showcase flow in LogCat (search text "VerboseLogging")
 */
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

        // Request that the chain makes up its mind on a content view...
        verboseLogging("internalOnCreate called. requesting content view...");
        setContentView(onRequestContentView(savedInstanceState, persistentState));

        // Give us a chance to inflate/add common elements in to the content view.
        verboseLogging(" .. done. inflating common elements...");
        internalInflateCommonElements(savedInstanceState, persistentState);

        // Give subclasses a chance to add/inflate anything they need to the content view before being bound.
        verboseLogging(" .. done. executing all pre bind creation...");
        onPreBindCreate(savedInstanceState, persistentState);

        // nuf-said
        verboseLogging(" .. done. binding... hold on to your butts!");
        ButterKnife.bind(this);

        // Now that we've bound-give subclasses a chance to do some more setup work.
        verboseLogging(" .. butts held tight enough-it WORKED! executing post bind creation...");
        onPostBindCreate(savedInstanceState, persistentState);

        // Our own, common, post-bind setup.
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
