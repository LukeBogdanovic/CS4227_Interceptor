package com.interceptor.interceptorpackage;

import com.interceptor.interceptorpackage.interfaces.IContext;
import com.interceptor.interceptorpackage.interfaces.Interceptor;

public class ConcreteLoggingInterceptor implements Interceptor {

    @Override
    public void preHandle(IContext contextObject) {
        if (contextObject instanceof LoggingContextObject) {
            LoggingContextObject context = (LoggingContextObject) contextObject;
            System.out.println("INFO: Customer: '" + context.getCustomer().getName() + "' has requested movie: '"
                    + context.getRental().getMovie().getTitle() + "' be added to their basket.");
        }
    }

    @Override
    public void postHandle(IContext contextObject) {
        if (contextObject instanceof LoggingContextObject) {
            LoggingContextObject context = (LoggingContextObject) contextObject;
            System.out.println(
                    "INFO: Customer: '" + context.getCustomer().getName() + "' is being redirected to their basket.");
        }
    }

}
