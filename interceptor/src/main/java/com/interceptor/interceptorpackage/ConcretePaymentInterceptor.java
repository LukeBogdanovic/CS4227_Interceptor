package com.interceptor.interceptorpackage;

import com.interceptor.interceptorpackage.interfaces.IContext;
import com.interceptor.interceptorpackage.interfaces.Interceptor;

public class ConcretePaymentInterceptor implements Interceptor {

    @Override
    public void preHandle(IContext contextObject) {
        if (contextObject instanceof PaymentContextObject) {
            PaymentContextObject context = (PaymentContextObject) contextObject;
            System.out.println("Payment of amount " + context.getAmount()
                    + " has been requested from the customer for rental of the movie: '" + context.getMovie().getTitle()
                    + "'.");
        }
    }

    @Override
    public void postHandle(IContext contextObject) {
        if (contextObject instanceof PaymentContextObject) {
            PaymentContextObject context = (PaymentContextObject) contextObject;
            System.out.println("Payment of amount " + context.getAmount()
                    + " has been received for the rental of movie: '" + context.getMovie().getTitle() + "'.");
        }
    }

}
