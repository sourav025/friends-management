package com.sps.friends.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendServiceTest {

    @Autowired
    private FriendService friendService;

    @Test
    public void makefriend() throws Exception {
        assertTrue(friendService.makefriend("a@gmail.com","b@gmail.com"));
        assertTrue(friendService.blockSubscription("b@gmail.com","g@gmail.com"));
        assertTrue(friendService.makefriend("c@gmail.com","b@gmail.com"));
        assertFalse(friendService.makefriend("g@gmail.com","b@gmail.com"));
    }

    @Test
    public void getFriends() throws Exception {
        assertTrue(friendService.makefriend("a1@gmail.com","b1@gmail.com"));
        assertTrue(friendService.makefriend("a2@gmail.com","b1@gmail.com"));
        assertTrue(friendService.makefriend("a2@gmail.com","c1@gmail.com"));
        assertEquals("[a1@gmail.com, a2@gmail.com]",friendService.getFriends("b1@gmail.com").toString());
        assertEquals("[b1@gmail.com, c1@gmail.com]",friendService.getFriends("a2@gmail.com").toString());
    }

    @Test
    public void getMutualFriends() throws Exception {
        assertTrue(friendService.makefriend("a1@gmail.com","b1@gmail.com"));
        assertTrue(friendService.makefriend("a2@gmail.com","b1@gmail.com"));
        assertTrue(friendService.makefriend("a2@gmail.com","c1@gmail.com"));
        assertEquals("[]",friendService.getMutualFriends("a1.gmail.com","a2@gmail.com").toString());
        assertTrue(friendService.makefriend("a1@gmail.com","c1@gmail.com"));
        List<String> mutual=friendService.getMutualFriends("a1@gmail.com","a2@gmail.com");
        Collections.sort(mutual);
        assertEquals("[b1@gmail.com, c1@gmail.com]",mutual.toString());
    }

    @Test
    public void subsribe() throws Exception {
        String requestor="111subcriber@gmail.com";
        String target="111target@gmail.com";
        friendService.makefriend(requestor, target);
        assertEquals("["+requestor+"]", friendService.postUpdate(target, "any text").toString());
    }

    @Test
    public void blockSubscription() throws Exception {
        String requestor="blker@gmail.com";
        String target="targetblock@gmail.com";

        // Block should be true
        assertTrue(friendService.blockSubscription(requestor, target));
        // requestor should not have any recipients unless somebody else subscribe
        assertEquals("[]", friendService.postUpdate(requestor, "any text").toString());
        // Blocked users should not be friends unless blocker removes the block
        assertFalse(friendService.makefriend(requestor, target) || friendService.makefriend(target, requestor) );
    }

    @Test
    public void postUpdate() throws Exception {
        String userEmail = "poster@yahoo.com";
        String text="hello name@anymail.com, this is a test to ensure quality." +
                "please inform all@group.com. don't miss anybody invalid@invalid. or invalid@inva_df best of luck" +
                "regards. xyx@lx.co.jp.";
        List<String> recipients = friendService.postUpdate(userEmail, text);
        Collections.sort(recipients);
        assertEquals("[all@group.com, name@anymail.com, xyx@lx.co.jp]", recipients.toString());
        friendService.blockSubscription(userEmail, "xyx@lx.co.jp");
        recipients = friendService.postUpdate(userEmail, text);
        Collections.sort(recipients);
        // Blocked User should not receive update
        assertEquals("[all@group.com, name@anymail.com]", recipients.toString());
    }

    @Test
    public void findTaggedEmails() throws Exception {
        List<String> tagged = friendService.findTaggedEmails("hello name@anymail.com, this is a test to ensure quality." +
                "please inform all@group.com. don't miss anybody invalid@invalid. or invalid@inva_df best of luck" +
                "regards. xyx@lx.co.jp.");
        // Ensure email extraction should work
        assertEquals("[name@anymail.com, all@group.com, xyx@lx.co.jp]",
                tagged.toString());
    }

}