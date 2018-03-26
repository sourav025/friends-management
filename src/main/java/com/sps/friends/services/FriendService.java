package com.sps.friends.services;

import java.util.List;

/**
 * Friend Service manages all the requests comes to the APIs
 */
public interface FriendService {
    /**
     *
     * Register user if not exists in friends management database,
     * links each other as friends
     *
     * @param userEmail
     * @param friendEmail
     *
     * @return boolean
     */
    boolean makefriend(String userEmail, String friendEmail);

    /**
     * Get All friend of a user
     *
     * @param email
     * @return List<String>
     */
    List<String> getFriends(String email);

    /**
     * Find common friends of provided two email address
     *
     * @param requestorEmail
     * @param targetEmail
     *
     * @return List<String>
     */
    List<String> getMutualFriends(String requestorEmail, String targetEmail);

    /**
     *
     * Subscribe for notifications of any updates
     *
     * @param requestorEmail
     * @param targetEmail
     * @return boolean
     */
    boolean subsribe(String requestorEmail, String targetEmail);

    /**
     *
     * Blocks subscription.
     * Finds all recepients and update block status
     *
     * @param requestorEmail
     * @param targetEmail
     * @return
     */
    boolean blockSubscription(String requestorEmail, String targetEmail);

    /**
     *
     * Post update
     *
     * @param userEmail
     * @return
     */
    List<String> postUpdate(String userEmail, String postText);
}
