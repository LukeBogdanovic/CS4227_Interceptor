package com.interceptor;

import com.interceptor.movierental.Customer;
import com.interceptor.interceptorpackage.ConcreteLoggingInterceptor;
import com.interceptor.interceptorpackage.Dispatcher;
import com.interceptor.interceptorpackage.interfaces.Interceptor;
import com.interceptor.movierental.Movie;
import com.interceptor.movierental.Rental;
import com.interceptor.interceptorpackage.ConcretePaymentInterceptor;

public class App {

    public static void main(String[] args) {
        Interceptor loggingInterceptor = new ConcreteLoggingInterceptor();
        Interceptor paymentInterceptor = new ConcretePaymentInterceptor();
        Dispatcher dispatcher = Dispatcher.getInstance();
        Customer customer = new Customer("Luke Bogdanovic", dispatcher);
        Movie movie = new Movie("Avatar", 0);

        dispatcher.register(paymentInterceptor);
        dispatcher.register(loggingInterceptor);

        customer.addRental(new Rental(movie, 5));

        System.out.println(customer.statement() + "\n");

        Movie movie2 = new Movie("Puss In Boots: The Last Wish", 1);

        customer.addRental(new Rental(movie2, 14));
        System.out.println(customer.statement());
    }

}