package com.meng.androidlifecyclerevampprototype;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * GranularLifecycle is a small proof of concept for adding turning the, Android provided, onCreate
 * method in to 3 separate methods.
 */
public interface GranularLifecycle {

    @LayoutRes int onRequestContentView(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState);
    void onPreBindCreate(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState);
    void onPostBindCreate(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState);

    interface VerboseLoggable {
    }

}
