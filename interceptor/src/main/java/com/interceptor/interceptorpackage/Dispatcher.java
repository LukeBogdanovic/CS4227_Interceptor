package com.interceptor.interceptorpackage;

import java.io.IOException;
import java.util.ArrayList;

public class Dispatcher {

    private ArrayList<Interceptor> interceptors;
    private static Dispatcher dispatcher = null;

    private Dispatcher() {
        interceptors = new ArrayList<>();
    }

    public static Dispatcher getInstance() throws IOException {
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

    public void dispatchClientRequestInterceptorPaymentService() throws IOException {
        ArrayList<Interceptor> interceptorList;
        interceptorList = interceptors;
        for (int i = 0; i < interceptorList.size(); i++) {
            ConcretePayInterceptor concretePayInterceptor = (ConcretePayInterceptor) interceptorList.get(i);
            concretePayInterceptor.processPayment(i);
        }
    }

}
