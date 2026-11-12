package com.adam.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Profile {
    private String name;
    private String bio;

    public Profile(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
}
