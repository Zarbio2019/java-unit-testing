package com.unittesting.data;

import com.unittesting.model.Message;
import com.unittesting.model.User;

public interface UsersRepository {
    boolean save(User user);

    Message printMessage(String msg, User user);
}
