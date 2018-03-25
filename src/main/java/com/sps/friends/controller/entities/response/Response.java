package com.sps.friends.controller.entities.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;

import java.util.List;


@JsonPropertyOrder( { "success", "friends", "recipients", "count"})
@Builder
public class Response {

    @JsonView(FriendView.Success.class)
    private boolean success = true;

    @JsonView(FriendView.Count.class)
    private int count = 0;

    @JsonView(FriendView.Friends.class)
    private List<String> friends;

    @JsonView(FriendView.Recipients.class)
    private List<String> recipients;

    public static interface FriendView {
        public interface Success {}

        public interface Count extends Success {}

        public interface Friends extends Count {}

        public interface Recipients extends Count {}
    }
}
