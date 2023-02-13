package com.interceptor.movierental;

import com.interceptor.interceptorpackage.Interceptor;

public class Rental {

    private Movie _movie;
    private int _daysRented;
    private Interceptor _paymentInterceptor;

    public Rental(Movie movie, int daysRented, Interceptor interceptor) {
        _movie = movie;
        _daysRented = daysRented;
        _paymentInterceptor = interceptor;
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

    public void processPayment() {
        double amount = getCharge();
        _paymentInterceptor.processPayment(amount);
    }

}
