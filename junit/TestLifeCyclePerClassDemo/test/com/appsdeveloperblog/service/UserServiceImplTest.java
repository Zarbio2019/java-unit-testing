package com.appsdeveloperblog.service;

import com.appsdeveloperblog.io.UsersDatabase;
import com.appsdeveloperblog.io.UsersDatabaseMapImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    UsersDatabase usersDatabase;
    UserService userService;
    String createdUserId="";

    @BeforeAll
    void setup() {
        // Create & initialize database
        usersDatabase = new UsersDatabaseMapImpl();
        usersDatabase.init();
        userService = new UserServiceImpl(usersDatabase);
    }

    @AfterAll
    void cleanup() {
        // Close connection
        // Delete database
        usersDatabase.close();
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {

        // Arrange
        Map<String, String> user = new HashMap<>();
        user.put("firstName","Sergey");
        user.put("lastName","Kargopolov");

        // Act
        createdUserId = userService.createUser(user);

        // Assert
        assertNotNull(createdUserId, "User id should not be null");
    }

    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {
        // Arrange
        Map<String, String> newUserDetails = new HashMap<>();
        newUserDetails.put("firstName","John");
        newUserDetails.put("lastName","Kargopolov");

        // Act
        Map updatedUserDetails = userService.updateUser(createdUserId, newUserDetails);

        // Assert
        assertEquals(newUserDetails.get("firstName"), updatedUserDetails.get("firstName"),
                "Returned value of user's first name is incorrect");
        assertEquals(newUserDetails.get("lastName"), updatedUserDetails.get("lastName"),
                "The returned value of user's last name is incorrect");
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {

        // Act
        Map userDetails = userService.getUserDetails(createdUserId);

        // Assert
        assertNotNull(userDetails, "User detailsshould not be null");
        assertEquals(createdUserId, userDetails.get("userId"),
                "Returned value details contains incorrect user id");
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {
        // Act
        userService.deleteUser(createdUserId);

        // Assert
        assertNull(userService.getUserDetails(createdUserId),
                "User should not been found");
    }
}
