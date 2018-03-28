package com.sps.friends.services;

import com.sps.friends.services.dtos.User;
import com.sps.friends.services.dtos.UsersRelation;
import com.sps.friends.services.relations.UserRelationService;
import com.sps.friends.services.users.UserService;
import com.sps.friends.services.validations.ValidationConsts;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FriendServiceImpl implements FriendService{

    private static Logger logger = LoggerFactory.getLogger(FriendServiceImpl.class);

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
        return user.getRelations().stream().filter(usersRelation -> usersRelation.isFriend()).map(usersRelation -> usersRelation.getFriendUserId().getEmail()).collect(Collectors.toList());
    }

    @Override
    public List<String> getMutualFriends(String requestorEmail, String targetEmail) {
        // Get userid instead of email address. If not present, create register as new user
        List<String> requestorFriends = getFriends(requestorEmail);
        List<String> targetFriends=getFriends(targetEmail);
        return requestorFriends.stream()
                .filter(friend -> targetFriends.contains(friend)).collect(Collectors.toList());
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
        findMentionAndAddAsFollower(userEmail, postText);
        User user = userService.findByEmail(userEmail);
        List<String> followersIdList = userRelationService.findFollower(user.getUserId());
        return followersIdList.stream().map(followersId->userService.findEmailById(followersId)).collect(Collectors.toList());
    }


    private void findMentionAndAddAsFollower(String poster, String postText) {

        Pattern emailPattern=Pattern.compile(ValidationConsts.EMAIL_VERIFICATION_REGEX);
        Matcher matcher = emailPattern.matcher(postText);
        while(matcher.find()){
            MatchResult matchResult =matcher.toMatchResult();
            String subsriber = matchResult.group(0);
            logger.info("Found {}. Subscribing to {}.", subsriber, poster);
            subsribe(subsriber, poster);
        }

//        TODO Improvement is needed
//        Pattern emailPattern = Pattern.compile(ValidationConsts.EMAIL_VERIFICATION_REGEX);
//        String[] emailTokens = postText.split("[,!`\"\\s+?]");
//        for(int i=0;i<emailTokens.length;i++){
//            String emailToken = emailTokens[i];
//
//            if( emailToken!=null && emailToken.length()>0
//                    && emailPattern.matcher(emailToken).matches()){
//                subsribe(emailToken, poster);
//            }
//        }
    }

}
