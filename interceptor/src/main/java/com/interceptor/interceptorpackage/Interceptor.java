package com.interceptor.interceptorpackage;

import java.io.IOException;

public interface Interceptor {
    public void processPayment(double amount) throws IOException;
}
