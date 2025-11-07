package com.unittesting.service;

import com.unittesting.data.UsersRepository;
import com.unittesting.model.Message;
import com.unittesting.model.User;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    EmailVerificationService emailVerificationService;

    // use constructor based dependency injection
    // allow test in isolation
    public UserServiceImpl(UsersRepository usersRepository,
                           EmailVerificationService emailVerificationService) {
        this.usersRepository = usersRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser(String firstName,
                           String lastName,
                           String email,
                           String password,
                           String repeatPassword) {

        // Validations
        if(firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("User's first name is empty");
        }

        if(lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("User's last name is empty");
        }

        // Create User object
        User user = new User(UUID.randomUUID().toString(), firstName, lastName, email);

        // Save User
        boolean isUserCreated;

        try {
            //UsersRepository usersRepository = new UsersRepositoryImpl(); // incorrect, is better to isolate
            isUserCreated = usersRepository.save(user);
        } catch (RuntimeException ex) {
            throw new UserServiceException(ex.getMessage());
        }

        if(!isUserCreated) throw new UserServiceException("Could not create user");

        // Email verification
        try {
            emailVerificationService.scheduleEmailConfirmation(user);
        } catch(RuntimeException ex) {
            throw new EmailNotificationServiceException(ex.getMessage());
        }

        return user;
    }

    @Override
    public void setMessage(String msg, User user) {
        Message message = usersRepository.printMessage(msg, user);
    }
}
