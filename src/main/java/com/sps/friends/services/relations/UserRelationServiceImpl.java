package com.sps.friends.services.relations;

import com.sps.friends.services.dtos.RelationKey;
import com.sps.friends.services.dtos.UsersRelation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserRelationServiceImpl implements UserRelationService{

    private UserRelationRepository userRelationRepository;

    @Override
    public boolean makeNewFriends(String userId, String friendId) {
        RelationKey userRelationKey = RelationKey.builder().userId(userId).friendUserId(friendId).build();
        RelationKey friendRelationKey = RelationKey.builder().userId(friendId).friendUserId(userId).build();
        return save(userRelationKey, true, false, false)!=null
                && save(friendRelationKey, true, false, false)!=null;
    }

    private UsersRelation save(RelationKey userRelationKey, boolean ifFriend, boolean ifFollowing, boolean ifBlocked){
        Optional<UsersRelation> optional = userRelationRepository.findById(userRelationKey);
        UsersRelation usersRelation = null;
        if(optional.isPresent()){
            usersRelation = optional.get();
        }
        else{
            usersRelation=new UsersRelation();
            usersRelation.setRelationKey(userRelationKey);
            usersRelation.setFriend(ifFriend);
            usersRelation.setFollowing(ifFollowing);
            usersRelation.setBlocked(ifBlocked);
        }
        return userRelationRepository.save(usersRelation);
    }

}
