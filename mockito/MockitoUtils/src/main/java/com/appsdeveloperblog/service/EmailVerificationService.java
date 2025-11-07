package com.appsdeveloperblog.service;

import com.appsdeveloperblog.model.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}
