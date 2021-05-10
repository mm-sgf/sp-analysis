package com.cfox.spanalysis.proxy;

public interface ContainerProxy<E> {

    void initProxy();

    void setProxyCallbackListener(ProxyCallbackListener<E> listener);
}
