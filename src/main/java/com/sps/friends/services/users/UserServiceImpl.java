package com.sps.friends.services.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sps.friends.services.dtos.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService{

    private UserRepository userRepo;

    @Override
    public User register(String email) {
        User dto = userRepo.findByEmail(email);
        if( dto==null ) {
            dto=new User();
            dto.setEmail(email);
            dto = userRepo.save(dto);
        }
        return dto;
    }

    @Override
    public User findByEmail(String email) {
        email=email.toLowerCase();
        return register(email);
    }

    public User findUserById(String userId) {
        Optional<User> optional = userRepo.findById(userId);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public String findEmailById(String userId) {
        User user = findUserById(userId);
        return user == null ? "" : user.getEmail();
    }
}
