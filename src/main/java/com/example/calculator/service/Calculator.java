package com.example.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    final static double PI = 3.142;

    public int sum(int a, int b) {
        return a + b;

    }
    public static void subtract(int a, int b) {
        for (int i = 0; i < 10; i++) {
            System.out.println("yea");// violation 'Must have at least one statement'
        }
    }
}
