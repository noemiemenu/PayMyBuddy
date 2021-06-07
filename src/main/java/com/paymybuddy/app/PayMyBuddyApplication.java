package com.paymybuddy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * The type Pay my buddy application.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PayMyBuddyApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PayMyBuddyApplication.class, args);
    }

}
