package com.interceptor.interceptorpackage;

import java.util.ArrayList;

import com.interceptor.interceptorpackage.interfaces.IContext;
import com.interceptor.interceptorpackage.interfaces.Interceptor;

public class Dispatcher {

    private ArrayList<Interceptor> interceptors;
    private static Dispatcher dispatcher = null;

    private Dispatcher() {
        this.interceptors = new ArrayList<>();
    }

    public static Dispatcher getInstance() {
        if (dispatcher == null) {
            dispatcher = new Dispatcher();
        }
        return dispatcher;
    }

    public void register(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public void remove(Interceptor interceptor) {
        interceptors.remove(interceptor);
    }

    public void dispatchClientRequestInterceptor(IContext contextObject) {
        for (Interceptor interceptor : interceptors) {
            interceptor.preHandle(contextObject);
            contextObject.execute(interceptor);
            interceptor.postHandle(contextObject);
        }
    }

}
