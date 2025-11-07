package com.unittesting.data;

import com.unittesting.model.Message;
import com.unittesting.model.User;

import java.util.HashMap;
import java.util.Map;

public class UsersRepositoryImpl implements UsersRepository {

    Map<String, User> users = new HashMap<>(); // used as database

    @Override
    /**
     * Save user in database Map<String, User>
     */
    public boolean save(User user) {

        boolean returnValue = false;

        if(!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            returnValue = true;
        }

        return returnValue;
    }

    @Override
    public Message printMessage(String msg, User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(msg).append(user);
        System.out.println(sb);

        Message message = new Message();
        message.setId("1");
        message.setDescription("description");
        return message;
    }
}
