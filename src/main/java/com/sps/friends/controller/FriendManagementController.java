package com.sps.friends.controller;

import com.sps.friends.controller.entities.request.ConnectionRequestEntity;
import com.sps.friends.controller.entities.request.PostUpdateRequestEntity;
import com.sps.friends.controller.entities.request.RequestEmail;
import com.sps.friends.controller.entities.request.UpdateRequestEntity;
import com.sps.friends.controller.entities.response.FriendsList;
import com.sps.friends.controller.entities.response.RecipientsList;
import com.sps.friends.controller.entities.response.Success;
import com.sps.friends.exceptions.ApiException;
import com.sps.friends.services.FriendService;
import com.sps.friends.services.validations.ValidationService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "FriendManagement Api", description = "All api is implemted here to mange friends connection.")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FriendManagementController {

    private FriendService friendService;
    private ValidationService validationService;

    @ApiOperation(value = "Api to make friendship.", response = Success.class)
    @RequestMapping(value="/makefriend", method = RequestMethod.PUT)
    public ResponseEntity<Success> makefriend(@RequestBody ConnectionRequestEntity requestEntity) throws ApiException{
        validationService.validateConnectionRequestEntity(requestEntity);
        boolean result = friendService.makefriend(requestEntity.getFriends().get(0), requestEntity.getFriends().get(1));
        return ResponseEntity.ok(new Success(result));
    }

    @ApiOperation(value = "Get all friends of a user",nickname = "friends", response = FriendsList.class)
    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    public ResponseEntity<FriendsList> retrieveFriends(@RequestBody RequestEmail request) throws ApiException{
        validationService.vaidateEmail(request.getEmail());
        List<String> friends = friendService.getFriends(request.getEmail());
        FriendsList friendsResponse = new FriendsList(friends);
        return ResponseEntity.ok(friendsResponse);
    }

    @ApiOperation(value = "Mutual Friends",nickname = "mutualfriends", response = FriendsList.class)
    @RequestMapping(value = "/mutualfriends", method = RequestMethod.POST)
    public ResponseEntity<FriendsList> mutualFriends(@RequestBody ConnectionRequestEntity requestEntity) throws ApiException{
        validationService.validateConnectionRequestEntity(requestEntity);
        List<String> mutualFriendsList=friendService.getMutualFriends(requestEntity.getFriends().get(0), requestEntity.getFriends().get(1));
        return ResponseEntity.ok(new FriendsList(mutualFriendsList));
    }

    @ApiOperation(value = "Subscribe for request",nickname = "subscription", response = Success.class)
    @RequestMapping(value = "/subscribe", method = RequestMethod.PUT)
    public ResponseEntity<Success> subscribe(@RequestBody UpdateRequestEntity requestEntity) throws ApiException{
        validationService.validateUpdateRequestEntity(requestEntity);
        boolean isSuscribed = friendService.subsribe(requestEntity.getRequestor(), requestEntity.getTarget());
        return ResponseEntity.ok(new Success(isSuscribed));
    }

    @ApiOperation(value = "Block recipient for subscription",nickname = "block", response = Success.class)
    @RequestMapping(value = "/block", method = RequestMethod.POST)
    public ResponseEntity<Success> block(@RequestBody UpdateRequestEntity requestEntity) throws ApiException{
        validationService.validateUpdateRequestEntity(requestEntity);
        boolean isBlocked = friendService.blockSubscription(requestEntity.getRequestor(), requestEntity.getTarget());
        return ResponseEntity.ok(new Success(isBlocked));
    }

    @ApiOperation(value = "Post a update and retrieve the recipients",nickname = "post", response = Success.class)
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<RecipientsList> postUpdate(@RequestBody PostUpdateRequestEntity postUpdateEntity) throws  ApiException{
        validationService.validatePostUpdateRequest(postUpdateEntity);
        List<String> followersEmail=friendService.postUpdate(postUpdateEntity.getSender(), postUpdateEntity.getText());
        return ResponseEntity.ok(new RecipientsList(followersEmail));
    }
}
