package com.sps.friends.services.validations;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sps.friends.controller.entities.request.ConnectionRequestEntity;
import com.sps.friends.exceptions.ApiException;
import com.sps.friends.exceptions.InvalidEmailException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceTest {

    @Autowired
    private ValidationService validationService;

    @Test(expected = InvalidEmailException.class)
    public void vaidateEmailInvalidTest() throws InvalidEmailException{
        validationService.vaidateEmail("heasdflasdf");
        validationService.vaidateEmail("hello");
    }

    @Test
    public void vaidateEmailValidTest() throws InvalidEmailException{
        assertTrue(validationService.vaidateEmail("sourav.sub@mail.com"));
        assertTrue(validationService.vaidateEmail("sov.sub1_3sov.sub1_3sov.sub1_3sov.sub1_3sov.sub1_3@maildmaildmaildmaildmaildmaildmaild.com"));
    }

    @Test
    public void validateConnectionRequestEntityTest() throws ApiException {
        ConnectionRequestEntity connectionRequestEntity = ConnectionRequestEntity.builder().friends(Arrays.asList("hello@yahoo.com","asdf@yahoo.cas")).build();
        assertTrue(validationService.validateConnectionRequestEntity(connectionRequestEntity));
    }
}