package com.sps.friends.services.validations;

import com.sps.friends.controller.entities.request.ConnectionRequestEntity;
import com.sps.friends.controller.entities.request.PostUpdateRequestEntity;
import com.sps.friends.controller.entities.request.UpdateRequestEntity;
import com.sps.friends.exceptions.ApiException;
import com.sps.friends.exceptions.InvalidEmailException;

/**
 * Validations of All Services
 */
public interface ValidationService {
    boolean vaidateEmail(String email) throws InvalidEmailException;

    boolean validateConnectionRequestEntity(ConnectionRequestEntity connectionRequestEntity) throws ApiException;

    boolean validateUpdateRequestEntity(UpdateRequestEntity updateRequestEntity) throws ApiException;

    boolean validatePostUpdateRequest(PostUpdateRequestEntity postUpdateEntity) throws ApiException;
}

