package com.itsm.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private String name;
    private List<String> hobbies;

    public User(String name, String... hobbies) {
        this.name = name;
        this.hobbies = Arrays.asList(hobbies);
    }
}
