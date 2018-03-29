package com.sps.friends.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sps.friends.services.dtos.User;
import com.sps.friends.services.relations.UserRelationService;
import com.sps.friends.services.users.UserService;
import com.sps.friends.services.validations.ValidationConsts;

import lombok.AllArgsConstructor;

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
        List<String> friendsIdList=userRelationService.findRelationsByUserId(user.getUserId());
        return friendsIdList.stream().map(friendId->userService.findEmailById(friendId)).collect(Collectors.toList());
    }

    @Override
    public List<String> getMutualFriends(String requestorEmail, String targetEmail) {
        List<String> requestorFriends = getFriends(requestorEmail);
        List<String> targetFriends=getFriends(targetEmail);
        return requestorFriends.stream()
                .filter(targetFriends::contains).collect(Collectors.toList());
    }

    @Override
    public boolean subsribe(String requestorEmail, String targetEmail) {
        User requestor = userService.findByEmail(requestorEmail);
        User target = userService.findByEmail(targetEmail);
        return userRelationService.subscribeForUpdate(requestor.getUserId(), target.getUserId());
    }

    @Override
    public boolean blockSubscription(String requestorEmail, String targetEmail) {
        User requestor = userService.findByEmail(requestorEmail);
        User target = userService.findByEmail(targetEmail);
        return userRelationService.blockForUpdate(requestor.getUserId(), target.getUserId());
    }

    @Override
    public List<String> postUpdate(String userEmail, String postText) {
        List<String> taggedEmails= findTaggedEmails(postText);
        taggedEmails.forEach(taggedEmail -> subsribe(taggedEmail, userEmail));
        User user = userService.findByEmail(userEmail);
        List<String> followersIdList = userRelationService.findFollower(user.getUserId());
        return followersIdList.stream().map(followersId->userService.findEmailById(followersId)).collect(Collectors.toList());
    }

    @Override
    public List<String> findTaggedEmails(String postText) {
        List<String> taggedEmails = new ArrayList<>();
        Pattern emailPattern=Pattern.compile(ValidationConsts.EMAIL_VERIFICATION_REGEX);
        Matcher matcher = emailPattern.matcher(postText);
        while(matcher.find()){
            MatchResult matchResult =matcher.toMatchResult();
            String taggedEmail = matchResult.group(0);
            taggedEmails.add(taggedEmail);
        }
        return taggedEmails;
    }

}
