package com.sps.friends.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sps.friends.controller.entities.request.ConnectionRequestEntity;
import com.sps.friends.controller.entities.request.RequestEmail;
import com.sps.friends.controller.entities.request.UpdateRequestEntity;
import com.sps.friends.controller.entities.request.PostUpdateRequestEntity;
import com.sps.friends.controller.entities.response.Response;
import com.sps.friends.exceptions.ApiException;
import com.sps.friends.services.FriendService;
import com.sps.friends.services.users.UserService;
import com.sps.friends.services.validations.ValidationService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@Api(value = "FriendManagement Api", description = "All api is implemted here to mange friends connection.")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FriendManagementController {

    private FriendService friendService;
    private ValidationService validationService;

    @ApiOperation(value = "Api to make frindship.", response = Response.class)
    @JsonView(Response.FriendView.Success.class)
    @RequestMapping(value="/makefriend", method = RequestMethod.PUT)
    public ResponseEntity<Response> makefriend(@RequestBody ConnectionRequestEntity requestEntity) throws ApiException{
        validationService.validateConnectionRequestEntity(requestEntity);
        String email1=requestEntity.getFriends().get(0);
        String email2=requestEntity.getFriends().get(1);
        boolean result = friendService.makefriend(email1, email2);
        return ResponseEntity.ok(Response.builder().success(result).build());
    }

    @ApiOperation(value = "Get all friends of a user",nickname = "friendslist", response = Response.class)
    @JsonView(Response.FriendView.Friends.class)
    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    public ResponseEntity<Response> retrieveFriends(@RequestBody RequestEmail request) throws ApiException{
        validationService.vaidateEmail(request.getEmail());
        List<String> friendsList = friendService.getFriends(request.getEmail());
        Response response=Response.builder().success(true).friends(friendsList).count(friendsList.size()).build();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Mutual Friends",nickname = "mutualfriends", response = Response.class)
    @JsonView(Response.FriendView.Friends.class)
    @RequestMapping(value = "/mutualfriends", method = RequestMethod.POST)
    public ResponseEntity<Response> mutualFriends(@RequestBody ConnectionRequestEntity requestEntity) throws ApiException{
        validationService.validateConnectionRequestEntity(requestEntity);
        List<String> mutualFriendsList=friendService.getMutualFriends(requestEntity.getFriends().get(0), requestEntity.getFriends().get(1));
        Response response = Response.builder().success(true).friends(mutualFriendsList).count(mutualFriendsList.size()).build();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Subscribe for request",nickname = "subscription", response = Response.class)
    @RequestMapping(value = "/subscribe", method = RequestMethod.PUT)
    @JsonView(Response.FriendView.Success.class)
    public ResponseEntity<Response> subscribe(@RequestBody UpdateRequestEntity requestEntity) throws ApiException{
        validationService.validateUpdateRequestEntity(requestEntity);
        boolean response = friendService.subsribe(requestEntity.getRequestor(), requestEntity.getTarget());
        return ResponseEntity.ok(Response.builder().success(response).build());
    }

    @ApiOperation(value = "Block recipient for subscription",nickname = "block", response = Response.class)
    @JsonView(Response.FriendView.Success.class)
    @RequestMapping(value = "/block", method = RequestMethod.POST)
    public ResponseEntity<Response> block(@RequestBody UpdateRequestEntity requestEntity) throws ApiException{
        validationService.validateUpdateRequestEntity(requestEntity);
        boolean isBlocked = friendService.blockSubscription(requestEntity.getRequestor(), requestEntity.getTarget());
        return ResponseEntity.ok(Response.builder().success(isBlocked).build());
    }

    @ApiOperation(value = "Post a update and retrieve the recipients",nickname = "post", response = Response.class)
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @JsonView(Response.FriendView.Recipients.class)
    public ResponseEntity<Response> postUpdate(@RequestBody PostUpdateRequestEntity postUpdateEntity) throws  ApiException{
        validationService.validatePostUpdateRequest(postUpdateEntity);
        List<String> followersEmail=friendService.postUpdate(postUpdateEntity.getSender(), postUpdateEntity.getText());
        return ResponseEntity.ok(Response.builder().recipients(followersEmail).count(followersEmail.size()).build());
    }
}
