package com.cfox.spanalysis.proxy;

import android.os.Build;

public class ProxyWorkFinishersManager {
    private final ContainerProxy<Runnable> mContainerProxy;

    public ProxyWorkFinishersManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mContainerProxy = new LinkedListProxy<>();
        } else {
            mContainerProxy = new ConcurrentLinkedQueueProxy<>();
        }
        mContainerProxy.initProxy();
    }

    public void setProxyCallbackListener(ProxyCallbackListener<Runnable> listener) {
        mContainerProxy.setProxyCallbackListener(listener);
    }
}
