package com.interceptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.interceptor.interceptorpackage.ConcretePayInterceptor;
import com.interceptor.interceptorpackage.Dispatcher;
import com.interceptor.interceptorpackage.Interceptor;
import com.interceptor.movierental.Customer;
import com.interceptor.movierental.Movie;
import com.interceptor.movierental.Rental;

/**
 * Unit test for simple App.
 */
public class AppTest {

    Customer c;
    Movie[] movies;

    @Before
    public void setUp() throws Exception {
        c = new Customer("Seanie Lambe");
        movies = new Movie[] {
                new Movie("Inglorius Basterds", Movie.REGULAR),
                new Movie("Shrek", Movie.CHILDRENS),
                new Movie("Avatar: The Way of Water", Movie.NEW_RELEASE),
                new Movie("Puss In Boots: The Last Wish", Movie.NEW_RELEASE),
                new Movie("Indiana Jones and The Raiders of the Lost Ark", Movie.REGULAR)
        };
    }

    @Test
    public void testPayment() throws IOException {
        Dispatcher dispatcher = Dispatcher.getInstance();
        Interceptor interceptor = new ConcretePayInterceptor();
        dispatcher.register(interceptor);

    }

}