package com.sps.friends.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sps.friends.controller.entities.request.ConnectionRequestEntity;
import com.sps.friends.controller.entities.request.RequestEmail;
import com.sps.friends.controller.entities.request.UpdateRequestEntity;
import com.sps.friends.controller.entities.request.PostUpdateRequestEntity;
import com.sps.friends.controller.entities.response.Response;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController("/")
@Api(value = "FriendManagement Api", description = "All api is implemted here to mange friends connection.")
public class FriendManagementController {

    @RequestMapping(value="makefriend", method = RequestMethod.PUT)
    @ApiOperation(value = "Api to make frindship.", response = Response.class)
    @JsonView(Response.FriendView.Success.class)
    public ResponseEntity<Response> makefriend(@RequestBody ConnectionRequestEntity requestEntity){
        return ResponseEntity.ok(Response.builder().build());
    }

    @ApiOperation(value = "getfriends",nickname = "friendlist", response = Response.class)
    @JsonView(Response.FriendView.Friends.class)
    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    public ResponseEntity<List<String>> retrieveFriends(@RequestBody RequestEmail request) {
        return ResponseEntity.ok(Arrays.asList("TODO implementation needed"));
    }

    @ApiOperation(value = "Mutual Friends",nickname = "mutialfriends list", response = Response.class)
    @JsonView(Response.FriendView.Friends.class)
    @RequestMapping(value = "/mutualfriends", method = RequestMethod.POST)
    public ResponseEntity<List<String>> mutualFriends(@RequestBody UpdateRequestEntity mutualFriendRequestEntity) {
        return ResponseEntity.ok(Collections.singletonList("TODO implementation needed"));
    }

    @ApiOperation(value = "Subscribe for request",nickname = "subscription", response = Response.class)
    @RequestMapping(value = "/subscribe", method = RequestMethod.PUT)
    @JsonView(Response.FriendView.Success.class)
    public ResponseEntity<Response> subscribe(@RequestBody UpdateRequestEntity requestEntity){
        return ResponseEntity.ok(Response.builder().friends(Collections.singletonList("TODO IMPL")).build());
    }

    @ApiOperation(value = "Block recipient for subscription",nickname = "block_subscription", response = Response.class)
    @JsonView(Response.FriendView.Success.class)
    @RequestMapping(value = "/block", method = RequestMethod.POST)
    public ResponseEntity<Response> block(@RequestBody UpdateRequestEntity requestEntity){
        return ResponseEntity.ok(Response.builder().friends(Collections.singletonList("TODO Implementation")).build());
    }

    @ApiOperation(value = "Post a update and retrieve the recipients",nickname = "subscription", response = Response.class)
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @JsonView(Response.FriendView.Recipients.class)
    public ResponseEntity<Response> postUpdate(@RequestBody PostUpdateRequestEntity postUpdateEntity){
        return ResponseEntity.ok(Response.builder().friends(Collections.singletonList("TODO: Implement post update for")).build());
    }
}
