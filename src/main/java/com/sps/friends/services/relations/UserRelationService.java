package com.sps.friends.services.relations;

import java.util.List;

/**
 * Service to manage relation and followers
 */
public interface UserRelationService {
    boolean makeNewFriends(String userId, String friendId);
    boolean subscribeForUpdate(String requestorId, String targetId);
    boolean blockForUpdate(String requestorId, String targetId);
    List<String> findFollower(String targetId);

    List<String> findRelationsByUserId(String userId);

}
