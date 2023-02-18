package com.interceptor.interceptorpackage;

import java.io.ObjectInputStream.GetField;

import com.interceptor.interceptorpackage.interfaces.IContext;
import com.interceptor.interceptorpackage.interfaces.Interceptor;
import com.interceptor.movierental.Movie;

public class PaymentContextObject implements IContext {

    private Movie movie;
    private double amount;

    public PaymentContextObject(Movie movie, double amount) {
        this.movie = movie;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public void execute(Interceptor interceptor) {
        if (interceptor instanceof ConcretePaymentInterceptor) {
            System.out.println(
                    "Customer has paid amount: " + getAmount() + " for the rental of movie: '"
                            + getMovie().getTitle()
                            + "'.");
        }
    }

}
