package com.sps.friends.services.users;

import org.springframework.data.repository.CrudRepository;

import com.sps.friends.services.dtos.User;

public interface UserRepository extends CrudRepository<User, String>{
    User findByEmail(String email);
}
