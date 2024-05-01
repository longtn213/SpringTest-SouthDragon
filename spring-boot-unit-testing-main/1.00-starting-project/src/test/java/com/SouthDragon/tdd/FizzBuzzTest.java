package com.SouthDragon.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FizzBuzzTest {

    // If number is divisible by 3, print Fizz
    // If number is divisible by 5, print Buzz
    // If number is divisible by 3 and 5 , print FizzBuzz
    // If number is NOT divisible by 3 or 5, then print the number

    @DisplayName("Divisible By Three")
    @Test
    @Order(1)
    void testForDivisibleByThree(){
        String expected = "Fizz";
        assertEquals(expected, FizzBuzz.compute(3), " Should return Fizz");
    }
    @DisplayName("Divisible By Five")
    @Test
    @Order(2)
    void testForDivisibleByFive(){
        String expected = "Buzz";
        assertEquals(expected, FizzBuzz.compute(5), " Should return Buzz");
    }
    @DisplayName("Divisible By Five And Three")
    @Test
    @Order(3)
    void testForDivisibleByFiveAndThree(){
        String expected = "FizzBuzz";
        assertEquals(expected, FizzBuzz.compute(15), " Should return FizzBuzz");
    }
    @DisplayName("Not Divisible By Five Or Three")
    @Test
    @Order(4)
    void testForNotDivisibleByFiveORThree(){
        String expected = "1";
        assertEquals(expected, FizzBuzz.compute(1), " Should return number : 1");
    }
    @DisplayName("Testing with Small data file")
    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Order(5)
    void testSmallDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }
    @DisplayName("Testing with Small data file")
    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    @Order(6)
    void testMediumDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }
    @DisplayName("Testing with Small data file")
    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    @Order(7)
    void testLargeDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }

}
