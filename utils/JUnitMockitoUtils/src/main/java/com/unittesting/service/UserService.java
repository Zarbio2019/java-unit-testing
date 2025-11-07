package com.unittesting.service;

import com.unittesting.model.User;

public interface UserService {
    User createUser(String firstName,
                    String lastName,
                    String email,
                    String password,
                    String repeatPassword);

    void setMessage(String msg, User user);
}
