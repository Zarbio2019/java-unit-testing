package com.unittesting.service;

import com.unittesting.model.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}
