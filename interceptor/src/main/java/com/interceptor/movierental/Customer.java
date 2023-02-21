package com.interceptor.movierental;

import java.util.ArrayList;
import java.util.List;

import com.interceptor.interceptorpackage.Dispatcher;
import com.interceptor.interceptorpackage.LoggingContextObject;
import com.interceptor.interceptorpackage.PaymentContextObject;

public class Customer {

    private String _name;
    private List<Rental> _rentals = new ArrayList<Rental>();
    private Dispatcher dispatcher;

    public Customer(String name, Dispatcher dispatcher) {
        _name = name;
        this.dispatcher = dispatcher;
    }

    /**
     * Adding a {@link Rental} to the _rentals {@link ArrayList}.
     * 
     * The Interception point for the logging interceptor and logging context object
     * called here
     * 
     * @param arg
     */
    public void addRental(Rental arg) {
        _rentals.add(arg);
        dispatcher.dispatchClientRequestInterceptor(new LoggingContextObject(this, arg));
    }

    public String getName() {
        return _name;
    }

    public List<Rental> getRentals() {
        return _rentals;
    }

    /**
     * The interception point for the payment interceptor and payment context object
     * called here
     * 
     * @return {@link String} result
     */
    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental : _rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.getCharge()) + "\n";
        }
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        dispatcher.dispatchClientRequestInterceptor(new PaymentContextObject(this, getTotalCharge()));
        return result;
    }

    public String htmlStatement() {
        String result = "<h1>Rentals for <em>" + getName() + "</em></h1><p>\n";
        for (Rental rental : _rentals) {
            result += rental.getMovie().getTitle() + ": " + String.valueOf(rental.getCharge()) + "<br>\n";
        }
        result += "<p>You owe <em>" + String.valueOf(getTotalCharge()) + "</em><p>\n";
        result += "On this rental you earned <em>" + String.valueOf(getTotalFrequentRenterPoints())
                + "</em> frequent renter points<p>";
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        for (Rental rental : _rentals) {
            result += rental.getCharge();
        }
        return result;
    }

    private double getTotalFrequentRenterPoints() {
        double result = 0;
        for (Rental rental : _rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }

}
