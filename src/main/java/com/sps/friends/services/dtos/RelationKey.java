package com.sps.friends.services.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RelationKey implements Serializable{

    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "friend_user_id", nullable = false)
    private String friendUserId;
}
