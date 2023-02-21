package com.interceptor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.interceptor.interceptorpackage.ConcreteLoggingInterceptor;
import com.interceptor.interceptorpackage.ConcretePaymentInterceptor;
import com.interceptor.interceptorpackage.Dispatcher;
import com.interceptor.interceptorpackage.interfaces.Interceptor;
import com.interceptor.movierental.Customer;
import com.interceptor.movierental.Movie;
import com.interceptor.movierental.Rental;

public class AppTest {

    static Customer customer;
    static Dispatcher dispatcher;
    Movie movie;
    Movie movie2;
    Movie movie3;
    static Interceptor loggingInterceptor;
    static Interceptor paymentInterceptor;
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() {
        dispatcher = Dispatcher.getInstance();
        customer = new Customer("Luke Bogdanovic", dispatcher);
        movie = new Movie("Avatar", 0);
        movie2 = new Movie("Puss In Boots: The Last Wish", 1);
        movie3 = new Movie("Shrek", 2);
    }

    @Test
    public void testMovieRentalSystem() {
        customer.addRental(new Rental(movie, 5));
        assertEquals("", systemOutRule.getLog().trim());
        systemOutRule.clearLog();
        loggingInterceptor = new ConcreteLoggingInterceptor();
        paymentInterceptor = new ConcretePaymentInterceptor();
        dispatcher.register(loggingInterceptor);
        dispatcher.register(paymentInterceptor);
        assertEquals("", systemOutRule.getLog().trim());
        systemOutRule.clearLog();
        customer.addRental(new Rental(movie2, 14));
        assertEquals(
                """
                        INFO: Customer: 'Luke Bogdanovic' has requested movie: 'Puss In Boots: The Last Wish' be added to their basket.
                        INFO: Customer: 'Luke Bogdanovic' has added Puss In Boots: The Last Wish to their basket.
                        INFO: Customer: 'Luke Bogdanovic' is being redirected to their basket.
                            """,
                systemOutRule.getLogWithNormalizedLineSeparator());
        systemOutRule.clearLog();
        System.out.println(customer.statement());
        // Can't create Text block String like before to make the expected string output
        // fit on one screen easily because of tabs in the output
        assertEquals(
                "Payment of amount 48.5 has been requested from the customer for rental of the movies: '[Avatar, Puss In Boots: The Last Wish]'.\nCustomer has paid amount: 48.5 for the rental of movies: '[Avatar, Puss In Boots: The Last Wish]'.\nPayment of amount 48.5 has been received for the rental of movies: '[Avatar, Puss In Boots: The Last Wish]'.\nRental Record for Luke Bogdanovic\n\tAvatar	6.5\n\tPuss In Boots: The Last Wish	42.0\nAmount owed is 48.5\nYou earned 3.0 frequent renter points",
                systemOutRule.getLogWithNormalizedLineSeparator().trim());
        systemOutRule.clearLog();
        customer.addRental(new Rental(movie3, 4));
        System.out.println(customer.statement());
        assertEquals(
                "INFO: Customer: 'Luke Bogdanovic' has requested movie: 'Shrek' be added to their basket.\nINFO: Customer: 'Luke Bogdanovic' has added Shrek to their basket.\nINFO: Customer: 'Luke Bogdanovic' is being redirected to their basket.\nPayment of amount 51.5 has been requested from the customer for rental of the movies: '[Avatar, Puss In Boots: The Last Wish, Shrek]'.\nCustomer has paid amount: 51.5 for the rental of movies: '[Avatar, Puss In Boots: The Last Wish, Shrek]'.\nPayment of amount 51.5 has been received for the rental of movies: '[Avatar, Puss In Boots: The Last Wish, Shrek]'.\nRental Record for Luke Bogdanovic\n\tAvatar	6.5\n\tPuss In Boots: The Last Wish	42.0\n\tShrek	3.0\nAmount owed is 51.5\nYou earned 4.0 frequent renter points\n",
                systemOutRule.getLogWithNormalizedLineSeparator());
    }

}
