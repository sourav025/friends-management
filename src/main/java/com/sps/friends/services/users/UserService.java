package com.sps.friends.services.users;

import com.sps.friends.services.dtos.User;

public interface UserService {
    public User register(String email);
    public User findByEmail(String email);
    public String findEmailById(String userId);
}
