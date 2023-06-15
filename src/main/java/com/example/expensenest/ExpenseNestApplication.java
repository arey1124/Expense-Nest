package com.example.expensenest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseNestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseNestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
