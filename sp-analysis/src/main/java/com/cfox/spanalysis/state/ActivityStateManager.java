package com.cfox.spanalysis.state;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cfox.spanalysis.proxy.ProxyWorkFinishersManager;

import java.util.HashMap;
import java.util.Map;

public class ActivityStateManager implements Application.ActivityLifecycleCallbacks {

    private final Map<String, SharedPrefTreeRecorder> mSharedPrefTreeRecorderMap = new HashMap<>();
    private final ProxyWorkFinishersManager mProxyWorkFinishersManager;
    public ActivityStateManager(ProxyWorkFinishersManager proxyWorkFinishersManager) {
        this.mProxyWorkFinishersManager = proxyWorkFinishersManager;
    }

    private SharedPrefTreeRecorder getActivityState(String activityName) {
        if (mSharedPrefTreeRecorderMap.containsKey(activityName)) {
            return mSharedPrefTreeRecorderMap.get(activityName);
        } else {
            SharedPrefTreeRecorder state = new SharedPrefTreeRecorder(activityName, mProxyWorkFinishersManager);
            mSharedPrefTreeRecorderMap.put(activityName, state);
            return state;
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        getActivityState(activity.getClass().getName()).onActivityStateChange(SharedPrefTreeRecorder.ActivityState.CREATED);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        getActivityState(activity.getClass().getName()).onActivityStateChange(SharedPrefTreeRecorder.ActivityState.STARTED);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        getActivityState(activity.getClass().getName()).onActivityStateChange(SharedPrefTreeRecorder.ActivityState.RESUMED);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        getActivityState(activity.getClass().getName()).onActivityStateChange(SharedPrefTreeRecorder.ActivityState.PAUSED);
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        getActivityState(activity.getClass().getName()).onActivityStateChange(SharedPrefTreeRecorder.ActivityState.STOPPED);
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        getActivityState(activity.getClass().getName()).onActivityStateChange(SharedPrefTreeRecorder.ActivityState.DESTROYED);
    }
}
