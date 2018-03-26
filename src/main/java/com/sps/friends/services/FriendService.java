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
     * @param email1
     * @param email2
     *
     * @return boolean
     */
    boolean makefriend(String email1, String email2);

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
     * @param email1
     * @param email2
     *
     * @return List<String>
     */
    List<String> getMutualFriends(String email1, String email2);

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
     * @param email
     * @return
     */
    List<String> postUpdate(String email, String postText);
}
