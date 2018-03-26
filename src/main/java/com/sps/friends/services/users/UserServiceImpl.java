package com.sps.friends.services.users;

import com.sps.friends.services.dtos.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService{

    private UserRepository userRepo;

    @Override
    public User register(String email) {
        User dto = findByEmail(email);
        if( dto==null ) {
            dto=new User();
            dto.setEmail(email);
            dto = userRepo.save(dto);
        }
        return dto;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
