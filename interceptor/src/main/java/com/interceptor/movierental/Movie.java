package com.interceptor.movierental;

import com.interceptor.interceptorpackage.Dispatcher;
import com.interceptor.interceptorpackage.PaymentContextObject;
import com.interceptor.interceptorpackage.interfaces.IContext;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int NEW_RELEASE = 1;
    public static final int REGULAR = 0;

    private String _title;
    private Price _price;
    private Dispatcher dispatcher;

    public Movie(String title, int priceCode, Dispatcher dispatcher) {
        _title = title;
        this.dispatcher = dispatcher;
        setPriceCode(priceCode);
    }

    private int getPriceCode() {
        return _price.getPriceCode();
    }

    private void setPriceCode(int arg) {
        switch (arg) {
            case Movie.REGULAR:
                _price = new RegularPrice();
                break;
            case Movie.CHILDRENS:
                _price = new ChildrensPrice();
                break;
            case Movie.NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
        IContext context = new PaymentContextObject(this, getCharge(arg));
        dispatcher.dispatchClientRequestInterceptor(context);
    }

    public String getTitle() {
        return _title;
    }

    public double getCharge(int daysRented) {
        return _price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return _price.getFrequentRenterPoints(daysRented);
    }

}
