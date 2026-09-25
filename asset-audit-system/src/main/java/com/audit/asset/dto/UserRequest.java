package com.audit.asset.dto;

import com.audit.asset.model.User;

public record UserRequest(
        String username,
        String password,
        String email,
        User.Role role
) {}