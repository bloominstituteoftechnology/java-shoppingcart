package com.lambdaschool.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class to start the application.
 */
@EnableJpaAuditing
@SpringBootApplication
public class ShoppingCartApplication
{
    /**
     * Main method to start the application.
     *
     * @param args Not used in this application.
     */
    public static void main(String[] args)
    {
        // now run the real application!
        SpringApplication.run(ShoppingCartApplication.class,
                              args);
    }
}
