package com.sps.friends.services.relations;

import com.sps.friends.services.dtos.User; /**
 * Service to manage relation and followers
 */
public interface UserRelationService {
    boolean makeNewFriends(String userId, String friendId);
}
