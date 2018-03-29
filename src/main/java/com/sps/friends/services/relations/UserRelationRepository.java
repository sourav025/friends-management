package com.sps.friends.services.relations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sps.friends.services.dtos.RelationKey;
import com.sps.friends.services.dtos.UsersRelation;

public interface UserRelationRepository extends JpaRepository<UsersRelation, RelationKey> {
    List<UsersRelation> findByRelationKeyFriendUserId(String friendUserId);
    List<UsersRelation> findByRelationKeyUserId(String userId);
}
