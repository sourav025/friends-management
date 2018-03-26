package com.sps.friends.services.validations;

import com.sps.friends.controller.entities.request.ConnectionRequestEntity;
import com.sps.friends.controller.entities.request.UpdateRequestEntity;
import com.sps.friends.exceptions.ApiException;
import com.sps.friends.exceptions.InvalidArgumentsException;
import com.sps.friends.exceptions.InvalidEmailException;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.regex.Pattern;

@Component
public class ValidationServiceDefaultImpl implements ValidationService {

    private static final String INVALID_EMAIL_MSG="Invalid Email Address. e.g. Expects: Email should be NonNull and 0 <= LENGTH_OF_EMAIL <= 255";
    private static final String INVALID_ARGUMENT_MSG ="Invalid requested api arguments. Excepted: 2 email address to make friendship.";
    private static final int VALID_MAKE_FRIENDS=2;

    private static final String EMAIL_VERIFICATION_REGEX = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    private static final int EMAIL_MAX_LEN=255;

    private boolean lengthCheck(String email) throws InvalidEmailException{
        if( email==null || email.isEmpty() || email.length() > 255 ) {
            InvalidEmailException.throwInvalidEmailException(INVALID_EMAIL_MSG);
        }
        return true;
    }

    private boolean regexCheck(String email) throws InvalidEmailException{
        if(!Pattern.compile(EMAIL_VERIFICATION_REGEX).matcher(email).matches()){
            InvalidEmailException.throwInvalidEmailException(INVALID_EMAIL_MSG);
        }
        return true;
    }

    @Override
    public boolean vaidateEmail(String email) throws InvalidEmailException{
        return lengthCheck(email) && regexCheck(email);
    }

    @Override
    public boolean validateConnectionRequestEntity(ConnectionRequestEntity connectionRequestEntity) throws ApiException {
        if(connectionRequestEntity==null || connectionRequestEntity.getFriends()==null
                || connectionRequestEntity.getFriends().size() != VALID_MAKE_FRIENDS){
            throw new InvalidArgumentsException(INVALID_ARGUMENT_MSG);
        }
        for(String email: connectionRequestEntity.getFriends()){
            vaidateEmail(email);
        }
        return true;
    }

    @Override
    public boolean validateUpdateRequestEntity(UpdateRequestEntity updateRequestEntity) throws ApiException{
        return vaidateEmail(updateRequestEntity.getRequestor()) && vaidateEmail(updateRequestEntity.getTarget());
    }
}
