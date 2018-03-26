package com.sps.friends.services.users;

import com.sps.friends.services.dtos.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID>{
    User findByEmail(String email);
}
