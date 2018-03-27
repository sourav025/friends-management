package com.sps.friends.services.validations;

import static com.sps.friends.services.validations.ValidationConsts.*;

import com.sps.friends.controller.entities.request.ConnectionRequestEntity;
import com.sps.friends.controller.entities.request.PostUpdateRequestEntity;
import com.sps.friends.controller.entities.request.UpdateRequestEntity;
import com.sps.friends.exceptions.ApiException;
import com.sps.friends.exceptions.InvalidArgumentsException;
import com.sps.friends.exceptions.InvalidEmailException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class ValidationServiceDefaultImpl implements ValidationService {

    private boolean emailLengthCheck(String email) throws InvalidEmailException{
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
        return emailLengthCheck(email) && regexCheck(email);
    }

    @Override
    public boolean validateConnectionRequestEntity(ConnectionRequestEntity connectionRequestEntity) throws ApiException {
        if(connectionRequestEntity==null || connectionRequestEntity.getFriends()==null
                || connectionRequestEntity.getFriends().size() != VALID_MAKE_FRIENDS){
            throw new InvalidArgumentsException(INVALID_ARGUMENT_MSG);
        }
        Set<String> hashSet=new HashSet<>();
        for(String email: connectionRequestEntity.getFriends()){
            vaidateEmail(email);
            hashSet.add(email);
        }
        if(hashSet.size()!=connectionRequestEntity.getFriends().size()){
            throw new InvalidArgumentsException(INVALID_DUPLICATE_FOUND_MSG);
        }
        return true;
    }

    @Override
    public boolean validateUpdateRequestEntity(UpdateRequestEntity updateRequestEntity) throws ApiException{
        String requestor=updateRequestEntity.getRequestor();
        String target=updateRequestEntity.getTarget();
        return vaidateEmail(requestor) && vaidateEmail(target)
                && !requestor.equalsIgnoreCase(target);
    }

    @Override
    public boolean validatePostUpdateRequest(PostUpdateRequestEntity postUpdateEntity) throws ApiException {
        vaidateEmail(postUpdateEntity.getSender());
        if( postUpdateEntity.getText()==null || postUpdateEntity.getText().isEmpty() ) {
            InvalidEmailException.throwInvalidEmailException(INVALID_POST_MSG);
        }
        return false;
    }
}
