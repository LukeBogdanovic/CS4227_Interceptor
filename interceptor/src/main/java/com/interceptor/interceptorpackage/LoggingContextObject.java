package com.interceptor.interceptorpackage;

import com.interceptor.interceptorpackage.interfaces.IContext;
import com.interceptor.interceptorpackage.interfaces.Interceptor;
import com.interceptor.movierental.Customer;
import com.interceptor.movierental.Rental;

public class LoggingContextObject implements IContext {

    private Customer customer;
    private Rental rental;

    public LoggingContextObject(Customer customer, Rental rental) {
        this.customer = customer;
        this.rental = rental;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Rental getRental() {
        return rental;
    }

    @Override
    public void execute(Interceptor interceptor) {
        if (interceptor instanceof ConcreteLoggingInterceptor) {
            System.out.println(
                    "INFO: Customer: '" + customer.getName() + "' has added " + rental.getMovie().getTitle()
                            + " to their basket.");
        }
    }

}
