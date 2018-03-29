package com.sps.friends.controller.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestEmail {
    @JsonProperty("email")
    private String email;
}
