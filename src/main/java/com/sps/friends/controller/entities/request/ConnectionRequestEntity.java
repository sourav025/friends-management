package com.sps.friends.controller.entities.request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConnectionRequestEntity {
    private List<String> friends;
}
