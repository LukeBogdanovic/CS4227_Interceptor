package com.interceptor.movierental;

import java.io.IOException;

import com.interceptor.interceptorpackage.ConcretePayInterceptor;
import com.interceptor.interceptorpackage.Dispatcher;
import com.interceptor.interceptorpackage.Interceptor;

public class Rental {

    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public double getCharge() {
        return _movie.getCharge(_daysRented);
    }

    public int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
    }

    public void processPayment(Customer customer) throws IOException {
        double amount = getCharge();
        Dispatcher dispatcher = Dispatcher.getInstance();
        Interceptor paymentInterceptor = new ConcretePayInterceptor();
        dispatcher.register(paymentInterceptor);
        dispatcher.dispatchClientRequestInterceptorPaymentService(amount);
    }

}
