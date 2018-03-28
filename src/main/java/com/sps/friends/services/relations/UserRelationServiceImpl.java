package com.sps.friends.services.relations;

import com.sps.friends.services.dtos.RelationKey;
import com.sps.friends.services.dtos.UsersRelation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserRelationServiceImpl implements UserRelationService{

    private UserRelationRepository userRelationRepository;

    @Override
    public boolean makeNewFriends(String userId, String friendId) {
        UsersRelation userRelation = findOrCreateNewUserRelation(userId, friendId);
        UsersRelation friendRelation = findOrCreateNewUserRelation(friendId, userId);

        // Relationship with block status can't be friends
        if( userRelation.isBlocked() || friendRelation.isBlocked() ){
            return false;
        }

        return setAsFriendAndSave(userRelation, friendRelation);
    }

    private boolean setAsFriendAndSave(UsersRelation... usersRelations){
        for(UsersRelation usersRelation : usersRelations) {
            usersRelation.setFriend(true);
            save(usersRelation);
        }
        return true;
    }

    @Override
    public boolean subscribeForUpdate(String requestorId, String targetId) {
        UsersRelation usersRelation = findOrCreateNewUserRelation(requestorId,targetId);
        UsersRelation friendsRelation = findOrCreateNewUserRelation(targetId,requestorId);
        if(!usersRelation.isBlocked() && !friendsRelation.isBlocked()) {
            usersRelation.setFollowing(true);
        }
        usersRelation=save(usersRelation);
        return usersRelation!=null && usersRelation.isFollowing();
    }

    @Override
    public boolean blockForUpdate(String requestorId, String targetId) {
        UsersRelation usersRelation = findOrCreateNewUserRelation(targetId,requestorId);
        usersRelation.setBlocked(true);
        usersRelation=save(usersRelation);
        return save(usersRelation)!=null && usersRelation.isBlocked();
    }

    @Override
    public List<String> findFollower(String targetId) {
        List<UsersRelation> usersRelations = userRelationRepository.findByRelationKeyFriendUserId(targetId);
        return usersRelations.stream()
                .filter(usersRelation -> !usersRelation.isBlocked())
                .map(usersRelation -> usersRelation.getRelationKey().getUserId())
                .collect(Collectors.toList());
    }

    private UsersRelation save(UsersRelation usersRelation){
        return userRelationRepository.save(usersRelation);
    }

    private UsersRelation findOrCreateNewUserRelation(String userId, String friendId ){
        RelationKey relationKey = RelationKey.builder().userId(userId).friendUserId(friendId).build();
        Optional<UsersRelation> optional = userRelationRepository.findById(relationKey);

        if(optional.isPresent()) {
            return optional.get();
        }

        UsersRelation usersRelation=new UsersRelation();
        usersRelation.setRelationKey(relationKey);

        return usersRelation;
    }
}
