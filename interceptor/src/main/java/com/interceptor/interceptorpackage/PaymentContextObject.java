package com.interceptor.interceptorpackage;

import com.interceptor.interceptorpackage.interfaces.IContext;
import com.interceptor.interceptorpackage.interfaces.Interceptor;
import com.interceptor.movierental.Customer;

public class PaymentContextObject implements IContext {

    private Customer customer;
    private double amount;

    public PaymentContextObject(Customer movie, double amount) {
        this.customer = movie;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void execute(Interceptor interceptor) {
        if (interceptor instanceof ConcretePaymentInterceptor) {
            System.out.println(
                    "Customer has paid amount: " + amount + " for the rental of movies: '"
                            + customer.getRentals().toString()
                            + "'.");
        }
    }

}
