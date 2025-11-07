package com.unittesting.model;

import java.util.StringJoiner;

public class Message {
    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
