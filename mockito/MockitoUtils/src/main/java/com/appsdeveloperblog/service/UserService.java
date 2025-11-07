package com.appsdeveloperblog.service;

import com.appsdeveloperblog.model.User;

public interface UserService {
    User createUser(String firstName,
                    String lastName,
                    String email,
                    String password,
                    String repeatPassword);
}
