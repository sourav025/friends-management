package com.sps.friends.controller.entities.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@JsonPropertyOrder({"success","friends","count"})
public class FriendsList extends Counts{
    private final List<String> friends;
    public FriendsList(List<String> friends){
        super(friends.size());
        this.friends=friends;
    }
}
