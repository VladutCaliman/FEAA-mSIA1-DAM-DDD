package com.adam.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Profile {
    private String name;
    private String bio;

    public Profile(String name,
                   String bio) {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Profile name cannot be null or blank");
        if (name.length() < 5 || name.length() > 25)
            throw new IllegalArgumentException("Profile name must be between 5 and 25 characters");

        if (bio == null)
            throw new IllegalArgumentException("Profile bio cannot be null");
        if (bio.length() > 255)
            throw new IllegalArgumentException("Profile bio cannot exceed 255 characters");

        this.name = name;
        this.bio = bio;
    }

    public void changeName(String newNameContender) {
        if (newNameContender == null || newNameContender.isBlank())
            throw new IllegalArgumentException("Profile name cannot be null or blank");
        if (newNameContender.length() < 5 || newNameContender.length() > 25)
            throw new IllegalArgumentException("Profile name must be between 5 and 25 characters");

        this.setName(newNameContender);
    }

    public void changeBio(String newBioContender) {
        if (newBioContender == null)
            throw new IllegalArgumentException("Profile bio cannot be null");
        if (newBioContender.length() > 255)
            throw new IllegalArgumentException("Profile bio cannot exceed 255 characters");

        this.setBio(newBioContender);
    }
}
