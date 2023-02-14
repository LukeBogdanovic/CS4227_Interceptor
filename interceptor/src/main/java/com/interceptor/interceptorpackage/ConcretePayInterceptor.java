package com.interceptor.interceptorpackage;

import java.io.IOException;

public class ConcretePayInterceptor implements Interceptor {

    @Override
    public void processPayment(double amount) throws IOException {
        System.out.printf("Payment of €%.2f has been processed.\n", amount);
    }

}
