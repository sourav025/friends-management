package com.sps.friends.controller.entities.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateRequestEntity {
    private String requestor;
    private String target;
}
