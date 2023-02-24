package com.interceptor.interceptorpackage;

import java.util.List;

import com.interceptor.interceptorpackage.interfaces.IContext;
import com.interceptor.interceptorpackage.interfaces.Interceptor;
import com.interceptor.movierental.Rental;

public class PaymentContextObject implements IContext {

    private List<Rental> rentals;
    private double amount;

    public PaymentContextObject(List<Rental> rentals, double amount) {
        this.rentals = rentals;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    @Override
    public void execute(Interceptor interceptor) {
        if (interceptor instanceof ConcretePaymentInterceptor) {
            System.out.println(
                    "Customer has paid amount: " + amount + " for the rental of movies: '"
                            + getRentals().toString()
                            + "'.");
        }
    }

}
