package com.sps.friends.services;

import com.sps.friends.services.dtos.User;
import com.sps.friends.services.users.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FriendServiceImpl implements FriendService{

    private UserService userService;

    @Override
    public boolean makefriend(String email1, String email2) {

        User user1 = userService.register(email1);
        User user2 = userService.register(email2);

        return false;
    }

    @Override
    public List<String> getFriends(String email) {
        User user = userService.register(email);


        return null;
    }

    @Override
    public List<String> getMutualFriends(String email1, String email2) {
        User user1 = userService.register(email1);
        User user2 = userService.register(email2);

        return null;
    }

    @Override
    public boolean subsribe(String requestorEmail, String targetEmail) {

        User requestor = userService.register(requestorEmail);
        User target = userService.register(targetEmail);


        return false;
    }

    @Override
    public boolean blockSubscription(String requestorEmail, String targetEmail) {
        User requestor = userService.register(requestorEmail);
        User target = userService.register(targetEmail);

        return false;
    }

    @Override
    public List<String> postUpdate(String email, String postText) {

        return null;
    }
}
