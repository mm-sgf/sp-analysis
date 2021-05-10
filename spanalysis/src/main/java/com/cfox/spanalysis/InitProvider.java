package com.cfox.spanalysis;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cfox.spanalysis.database.DBManager;
import com.cfox.spanalysis.proxy.ProxyWorkFinishersManager;
import com.cfox.spanalysis.state.ActivityStateManager;


public class InitProvider extends ContentProvider {
    private static final String TAG = "InitProvider";

    @Override
    public boolean onCreate() {
        DBManager.initDBRoom(getContext());
        registerActivityLifecycle(getContext());
        return true;
    }

    private void registerActivityLifecycle(Context context) {
        Log.d(TAG, "registerActivityLifecycle: SDK code:" + Build.VERSION.SDK_INT );
        ActivityStateManager sActivityStateManager = new ActivityStateManager(new ProxyWorkFinishersManager());
        ((Application)context.getApplicationContext()).registerActivityLifecycleCallbacks(sActivityStateManager);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
