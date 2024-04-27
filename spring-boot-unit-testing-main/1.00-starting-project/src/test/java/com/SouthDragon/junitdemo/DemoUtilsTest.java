package com.SouthDragon.junitdemo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach(){
        demoUtils = new DemoUtils();

        System.out.println("@BeforeEach executes before the execution of each test method");
    }

    @AfterEach
    void setupAfterEach(){
        System.out.println("Running @AfterEach");
        System.out.println("");
    }

    @BeforeAll
    static void setupBeforeEachClass(){
        System.out.println("@BeforeAll executes only once before all test methods execution in class");
    }


    @AfterAll
    static void setupAfterEachClass(){
        System.out.println("@AfterAll executes only once after all test methods execution in class");
    }

    @Test
    void testEqualsAndNotEquals(){
        System.out.println("Running test: testEqualsAndNotEquals");


        assertEquals(6, demoUtils.add(2, 4), "2 + 4 must be 6 ");
        assertNotEquals(6, demoUtils.add(1, 9), "1 + 9 must not be 10 ");

    }

    @Test
    void testNullAndNotNull(){
        System.out.println("Running test: testNullAndNotNull");

        String str1 = null;
        String str2 = "southDragon";

        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should be not null");
    }
}
