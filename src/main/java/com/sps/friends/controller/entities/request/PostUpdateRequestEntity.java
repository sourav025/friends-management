package com.sps.friends.controller.entities.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PostUpdateRequestEntity {
    private String sender;
    private String text;
}
