package com.adam.user.domain;

import java.util.Map;

public interface TokenProvider {
    public String generateTokenFrom(Account account);
    public Map<String, Object> extractClaimsFrom(String jwt);
}
