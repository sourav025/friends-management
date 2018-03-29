package com.sps.friends.controller.entities.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonPropertyOrder({"success","friends","count"})
public class FriendsList extends Counts{
    private final List<String> friends;
    public FriendsList(List<String> friends){
        super(friends.size());
        this.friends=friends;
    }
}
