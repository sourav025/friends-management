package com.sps.friends.services.relations;

import com.sps.friends.services.dtos.RelationKey;
import com.sps.friends.services.dtos.UsersRelation;
import org.springframework.data.repository.CrudRepository;

public interface UserRelationRepository extends CrudRepository<UsersRelation, RelationKey>{
}
