package com.cfox.spanalysis.proxy;

public interface ProxyCallbackListener<E> {

    void add(E e);

    void remove(Object o);
}
