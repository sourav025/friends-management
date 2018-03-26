package com.sps.friends.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "user_id", nullable = false)
    String userId;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    public String toString(){
        return String.format("User [ user_id=%s and email=%s ]", userId, email);
    }
}
