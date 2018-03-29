package com.sps.friends.controller.entities.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostUpdateRequestEntity {
    private String sender;
    private String text;
}
