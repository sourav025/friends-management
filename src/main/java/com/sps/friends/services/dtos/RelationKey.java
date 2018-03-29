package com.sps.friends.services.dtos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RelationKey implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2138998047395171015L;
	
	@Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "friend_user_id", nullable = false)
    private String friendUserId;
}
