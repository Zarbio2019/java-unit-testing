package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

// DisplayName: name test less technical and more friendly
@DisplayName("Test Math operations in Calculator class")

/*******************************************/

// Order annotation

// 1. Order to run test methods in a Class
/*
@TestMethodOrder(MethodOrderer.Random.class) // by default is not random, is deterministic but intentionally nonobvious
//@TestMethodOrder(MethodOrderer.MethodName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // by order index
    @Order(1)
    @Test
    void testA()

    @Order(2)
    @Test
    void testB()
*/

// 2. Order to run test classes
// Run All the test
/*
    @OrderServiceTest, @Order(3)
    @ProductServiceTest, @Order(2)
    @UserServiceTest, @Order(1)
*/

// or:
// Create a resource file
/*
    create resource folder, create New, Resource Bundle, name: junit-platform, it creates junit-platform.properties
    Run All the test
*/

// 3. Order to run test per instance
// useful when many methods share the same object instance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
    // @BeforeAll, @AfterAll don't need to be static
// ref: MethodsOrderedByOrderIndexTest.java

// Demo: junit\TestLifeCyclePerClassDemo

/*******************************************/

class MainTest {

    Main oper;

    // JUnit Test Lifecycle
    /*
    @BeforeAll
        setup()

      // Class instance 1
      @BeforeEach
        beforeEach()

      @Test
        Test method

      @AfterEach
        afterEach()

      // Class instance 2
      @BeforeEach
        beforeEach()

      @Test
        Test method

      @AfterEach
        afterEach()

    @AfterAll
        cleanup()
     */

    // at the beginning, execute before all test methods
    // e.g.: create database
    @BeforeAll
    static void setup() {
    }

    @AfterAll // at the end, execute after all test methods
    // for cleanup purposes
    // e.g.: close database connection
    static void cleanup() {
    }

    @BeforeEach // execute before each test method
    // e.g.: if all of your test methods need to create the same object,
    // then you will move this object creation to a before each meant it.
    void beforeEach() {
        oper = new Main();
    }

    @AfterEach // execute after each test method
    // for cleanup purposes
    // e.g.: close database connection, cleanup resources
    void afterEach() {
    }

    @Test
    void main() {
        System.out.println("Demo test");
    }

    /*******************************************/

    // Test name
    // should be descriptive
    // test<System Under Test/Method name>_<Condition or State Change>_<Expected Result>
	// e.g.: void testDivision_WhenFourIsDividedByTwo_ShouldReturnTwo()

    /*******************************************/

    // Disable test
    @Disabled("TODO: Still need to work on it")
    // or comment: //@Test
    @Test
    void sampleTest() {
    }

    /*******************************************/

    // Assertion
    @DisplayName("Test 4/2 = 2")
    @Test
    void testDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {

        // Test structure: AAA
        // Arrange (Given): Initialize
        int dividend = 4, divisor = 2;
        int expectedResult = 2;

        // Act (When)
        int actualResult = oper.division(dividend, divisor);

        // Assert (Then)
        assertEquals(2, actualResult, "The division() did not produce expected result");
        // optimize performance
        assertEquals(expectedResult, actualResult, ()-> dividend + "/" + divisor + "did not produce " + expectedResult);

        int actual = 1;
        int expected = 1;
        assertSame(expected, actual);
        assertTrue(expected == actual);
        assertFalse('a' > 'b', ()->"optional failure message");
        assertNotNull(actual);
        //assertNull(actual);
        //fail("failed"); // intentionally fails test method without checking for any conditions
    }

    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenDividendIsDividendByZero_ShouldThrowException() {
        try {
            fail("failed");
        } catch(Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    /*******************************************/

    // Assert and exception
    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenDividendIsDividendByZero_ShouldThrowArithmeticException() {
        System.out.println("Running Division by zero");

        // Arrange
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        // Act & Assert
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            // Act
            oper.division(dividend, divisor);
        }, "Division by zero should have thrown an Arithmetic exception.");

        // Assert
        assertEquals(expectedExceptionMessage, actualException.getMessage(),
        "Unexpected exception message");
    }

    /*******************************************/

    // Parameterized Test to accept multiple arguments

    // 1. MethodSource
    @DisplayName("Test integer subtraction [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
    //@MethodSource("integerSubtractionInputParameters") // method name, or
    @MethodSource() // use this test name equals to the method source name
    void integerSubtraction(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test "+minuend+" "+subtrahend+"="+expectedResult);

        int actualResult = oper.subtraction(minuend, subtrahend);

        assertEquals(expectedResult, actualResult,
                () -> minuend + "-" + subtrahend + "did not produce" + expectedResult);
    }

    // method source: can be same name of the test
    private static Stream<Arguments> integerSubtraction() {
        return Stream.of(
                Arguments.of(33, 1, 32),
                Arguments.of(54, 1, 53),
                Arguments.of(24, 1, 23)
        );
    }

    // 2. CsvSource: comma separated values
    @DisplayName("Test integer subtraction [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
    @CsvSource({
            "33, 1, 32",
            "24, 1, 23",
            "54, 1, 53"
    })
//    @CsvSource({
//            "apple, orange",
//            "apple, ''", // empty
//            "apple," // null
//    })
    void integerSubtractionCsvSource(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test "+minuend+" "+subtrahend+"="+expectedResult);

        int actualResult = oper.subtraction(minuend, subtrahend);

        assertEquals(expectedResult, actualResult,
                () -> minuend + "-" + subtrahend + "did not produce" + expectedResult);
    }

    // 3. CsvFileSource: long list of values
    @DisplayName("Test integer subtraction [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
    @CsvFileSource(resources = "/integerSubtractionCsvFileSource.csv")
    /* create a file for test in IntelliJ IDEA:
    1. create package "resources"
    2. go File/Open Module Settings, Modules, Sources, Test Resources, select the package resources and OK
    or double click in the folder
    */
    void integerSubtractionCsvFileSource(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test "+minuend+" "+subtrahend+"="+expectedResult);

        int actualResult = oper.subtraction(minuend, subtrahend);

        assertEquals(expectedResult, actualResult,
                () -> minuend + "-" + subtrahend + "did not produce" + expectedResult);
    }

    // 4. ValueSource
    @ParameterizedTest
    @ValueSource(strings={"John", "Kate", "Alice"}) // only accept 1 argument
    void valueSourceDemostration(String firstName){
        System.out.println(firstName);
        assertNotNull(firstName);
    }

    /*******************************************/

    // Repeated Tests
    // execute same test many times
    @DisplayName("Division by zero")
    //@RepeatedTest(3) // 3 times
    //void testIntegerDivision_WhenDividendIsDividendByZero_ShouldThrowArithmeticExceptionRep() {
    // or:
    @RepeatedTest(value=3, name="{displayName}. Repetition {curentRepetition} of " +
        "{totalRepetitions}")
    void testIntegerDivision_WhenDividendIsDividendByZero_ShouldThrowArithmeticExceptionRep(
            RepetitionInfo repetitionInfo,
            TestInfo testInfo) {
        System.out.println("Running " + testInfo.getTestMethod().get().getName());
        System.out.println("Repetition #" + repetitionInfo.getCurrentRepetition() +
                "of " + repetitionInfo.getTotalRepetitions());

        System.out.println("Running Division by zero");

        // Arrange
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        // Act & Assert
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            // Act
            oper.division(dividend, divisor);
        }, "Division by zero should have thrown an Arithmetic exception.");

        // Assert
        assertEquals(expectedExceptionMessage, actualException.getMessage(),
                "Unexpected exception message");
    }
}