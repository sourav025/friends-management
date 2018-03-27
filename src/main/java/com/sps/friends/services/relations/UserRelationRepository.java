package com.sps.friends.services.relations;

import com.sps.friends.services.dtos.RelationKey;
import com.sps.friends.services.dtos.UsersRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRelationRepository extends JpaRepository<UsersRelation, RelationKey> {
    List<UsersRelation> findByRelationKeyFriendUserId(String friendUserId);
}
