package com.cfox.spanalysis.proxy;

import android.annotation.SuppressLint;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueProxy<E> extends ConcurrentLinkedQueue<E> implements ContainerProxy<E> {
    private static final String TAG = "ConcurrentLinkedQueuePr";

    private ProxyCallbackListener<E> mListener;
    @Override
    public void initProxy() {
        try {
            Log.d(TAG, "initProxy: start ");
            String CLASS_QUEUED_WORK = "android.app.QueuedWork";
            String FIELD_PENDING_FINISHERS = "sPendingWorkFinishers";

            @SuppressLint("PrivateApi")
            Class<?> clazz = Class.forName(CLASS_QUEUED_WORK);
            Field field = clazz.getDeclaredField(FIELD_PENDING_FINISHERS);
            field.setAccessible(true);
            field.set(null, this);
            Log.d(TAG,"initProxy success");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setProxyCallbackListener(ProxyCallbackListener<E> listener) {
        this.mListener = listener;
    }


    @Override
    public boolean add(E e) {
        if (mListener != null) {
            mListener.add(e);
        }
        return super.add(e);
    }

    @Override
    public boolean remove(Object o) {
        if (mListener != null) {
            mListener.remove(o);
        }
        return super.remove(o);
    }

}
