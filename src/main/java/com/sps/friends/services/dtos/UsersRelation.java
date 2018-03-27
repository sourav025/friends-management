package com.sps.friends.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UsersRelation {
    @EmbeddedId
    private RelationKey relationKey;

    @Column(name = "is_friend")
    private boolean friend;

    @Column(name = "is_following")
    private boolean following;

    @Column(name = "is_blocked")
    private boolean blocked;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User friendUserId;

}
