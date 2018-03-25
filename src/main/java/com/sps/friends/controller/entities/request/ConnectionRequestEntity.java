package com.sps.friends.controller.entities.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ConnectionRequestEntity {
    private List<String> friends;
}
