package com.sps.friends.services.validations;

public class ValidationConsts {
    public static final String INVALID_EMAIL_MSG="Invalid Email Address. e.g. Expects: Valid email address and 5 < LENGTH_OF_EMAIL < 255";
    public static final String INVALID_ARGUMENT_MSG ="Invalid requested Api arguments. Excepted: 2 email address to make friendship.";
    public static final int VALID_MAKE_FRIENDS=2;
    public static final String INVALID_POST_MSG ="Invalid requested Api arguments. Excepted: Valid email address and NonEmpty text to post update.";
    public static final String INVALID_DUPLICATE_FOUND_MSG="Invalid duplicate emails found. Duplicate not allowed.";

    public static final String EMAIL_VERIFICATION_REGEX = "([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}([\\.]{1,1}[\\w&&[^_]]{2,}){1,3}";
    public static final int EMAIL_MAX_LEN=255;
}
