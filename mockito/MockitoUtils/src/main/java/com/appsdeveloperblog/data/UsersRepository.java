package com.appsdeveloperblog.data;

import com.appsdeveloperblog.model.User;

public interface UsersRepository {
    boolean save(User user);
}
