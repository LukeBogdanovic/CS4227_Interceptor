package com.interceptor.interceptorpackage.interfaces;

public interface Interceptor {
    public void preHandle(IContext contextObject);

    public void postHandle(IContext contextObject);
}
