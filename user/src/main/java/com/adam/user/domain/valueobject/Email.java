package com.adam.user.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Locale;

@Getter @EqualsAndHashCode
public class Email {
    private static final String RGX =
            "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

    private String value;

    public Email(String value)  {
        if (value == null ||
                !value.toLowerCase(Locale.ROOT).matches(RGX)) {
            throw new IllegalArgumentException("Invalid email");
        }

        this.value = value.toLowerCase(Locale.ROOT);
    }

    @Override public String toString() {
        return value;
    }
}
