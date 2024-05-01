package com.SouthDragon.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach() {
        demoUtils = new DemoUtils();

        System.out.println("@BeforeEach executes before the execution of each test method");
    }
    @Test
    @DisplayName("Multiply")
    void testMultiply() {
        System.out.println("Running test: testEqualsAndNotEquals");


        assertEquals(6, demoUtils.multiply(2, 3), "2 * 3 must be 6 ");

    }
    @Test
    @DisplayName("Equals and Not Equals")
    @Order(-4)
    void testEqualsAndNotEquals() {
        System.out.println("Running test: testEqualsAndNotEquals");


        assertEquals(6, demoUtils.add(2, 4), "2 + 4 must be 6 ");
        assertNotEquals(6, demoUtils.add(1, 9), "1 + 9 must not be 10 ");

    }

    @Test
    @DisplayName("Null and Not Null")
    @Order(-1)
    void testNullAndNotNull() {
        System.out.println("Running test: testNullAndNotNull");

        String str1 = null;
        String str2 = "southDragon";

        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should be not null");
    }

    @Test
    @DisplayName("Same And Not Same")
    @Order(-2)
    void testSameAndNotSame() {
        System.out.println("Running test: testSameAndNotSame");

        String str = "SouthDragon";

        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Object should refer to the same object");
        assertNotSame(demoUtils.getAcademy(), str, "Object should not refer to the same object");
    }

    @Test
    @DisplayName("True And False")
    void testTrueAndFalse() {
        System.out.println("Running test: testTrueAndFalse");

        int gradeOne = 10;
        int gradeTwo = 2;

        assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), "This should be true");
        assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), "This should be false");
    }

    @Test
    @DisplayName("Arrays Equals")
    void testArraysEquals() {
        System.out.println("Running test: testArraysEquals");

        String[] StringArrays = {"A", "B", "C"};

        assertArrayEquals(demoUtils.getFirstThreeLettersOfAlphabet(), StringArrays, "Arrays should be equals");
    }

    @Test
    @DisplayName("Iterable Equals")
    void testIterableEquals() {
        System.out.println("Running test: testIterableEquals");

        List<String> theList = List.of("luv", "2", "code");

        assertIterableEquals(demoUtils.getAcademyInList(), theList, "Expected List should be as same as Actual list");

    }

    @Test
    @DisplayName("Lines Match")
    void testLinesMatch() {
        System.out.println("Running test: testLinesMatch");

        List<String> theList = List.of("luv", "2", "code");

        assertLinesMatch(demoUtils.getAcademyInList(), theList, "Lines should be match");
    }

    @Test
    @DisplayName("Throw And Does Not Throw")
    void testThrowANdDoesNotThrow() {
        System.out.println("Running test: testThrowANdDoesNotThrow");

        assertThrows(Exception.class, () -> {
            demoUtils.throwException(-1);
        }, "Should throw exception");
        assertDoesNotThrow(() -> {
            demoUtils.throwException(9);
        }, "Should not be throw exception");

    }

    @Test
    @DisplayName("TimeOut")
    void testTimeOut() {
        System.out.println("Running test: testTimeOut");

        assertTimeout(Duration.ofSeconds(3),()->{demoUtils.checkTimeout();},"Method should execute in 3 seconds");
    }
//    @AfterEach
//    void setupAfterEach() {
//        System.out.println("Running @AfterEach");
//        System.out.println("");
//    }
//
//    @BeforeAll
//    static void setupBeforeEachClass() {
//        System.out.println("@BeforeAll executes only once before all test methods execution in class");
//    }
//
//
//    @AfterAll
//    static void setupAfterEachClass() {
//        System.out.println("@AfterAll executes only once after all test methods execution in class");
//    }

}
