package com.meng.androidlifecyclerevampprototype;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

public interface GranularLifecycle {

    @LayoutRes int onRequestContentView(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState);
    void onPreBindCreate(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState);
    void onPostBindCreate(@Nullable final Bundle savedInstanceState, @Nullable final PersistableBundle persistentState);

    interface VerboseLoggable {
    }

}
