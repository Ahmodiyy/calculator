package com.example.calculator.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceTest {
    private final CalculatorService calculator = new CalculatorService();

    @Test
    public void testSum() {
        assertEquals(5, calculator.sum(2, 3));
    }
}
