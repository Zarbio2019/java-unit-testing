package org.example;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // one instance per class
//@TestInstance(TestInstance.Lifecycle.PER_METHOD) // one instance per method
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodsOrderedByOrderIndexTest {

    StringBuilder completed = new StringBuilder("");

    @AfterEach
    void afterEach(){
        System.out.println("The state of instance object is " + completed);
    }

    @Order(1)
    @Test
    void testC() {
        System.out.println("Running test C");
        completed.append("1");
    }

    @Order(2)
    @Test
    void testA() {
        System.out.println("Running test A");
        completed.append("2");
    }

    @Order(3)
    @Test
    void testB() {
        System.out.println("Running test B");
        completed.append("3");
    }
}
