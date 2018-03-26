package com.sps.friends.services;

import com.sps.friends.services.dtos.User;
import com.sps.friends.services.relations.UserRelationService;
import com.sps.friends.services.users.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FriendServiceImpl implements FriendService{

    private UserService userService;
    private UserRelationService userRelationService;

    @Override
    @Transactional
    public boolean makefriend(String userEmail, String friendEmail) {
        User user1 = userService.findByEmail(userEmail);
        User user2 = userService.findByEmail(friendEmail);
        return userRelationService.makeNewFriends(user1.getUserId(), user2.getUserId());
    }

    @Override
    public List<String> getFriends(String email) {
        User user = userService.findByEmail(email);
        if(user.getRelations()==null){
            return Collections.emptyList();
        }
        return user.getRelations().stream().map(usersRelation -> usersRelation.getFriendUserId().getEmail()).collect(Collectors.toList());
    }

    @Override
    public List<String> getMutualFriends(String requestorEmail, String targetEmail) {
        // Get userid instead of email address. If not present, create register as new user
        User user1 = userService.findByEmail(requestorEmail);
        User user2 = userService.findByEmail(targetEmail);
        List<String> requestorFriends = getFriends(requestorEmail);
        List<String> targetFriends=getFriends(targetEmail);
        return requestorFriends.stream()
                .filter(friend -> targetFriends.contains(friend)).collect(Collectors.toList());
    }

    @Override
    public boolean subsribe(String requestorEmail, String targetEmail) {

        User requestor = userService.findByEmail(requestorEmail);
        User target = userService.findByEmail(targetEmail);
        return false;
    }

    @Override
    public boolean blockSubscription(String requestorEmail, String targetEmail) {
        User requestor = userService.findByEmail(requestorEmail);
        User target = userService.findByEmail(targetEmail);

        return false;
    }

    @Override
    public List<String> postUpdate(String userEmail, String postText) {

        return null;
    }
}
