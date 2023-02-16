package com.interceptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

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
        c.addRental(new Rental(movies[1], 3));
    }

    @Test
    public void testPayment() throws IOException {

    }

}
