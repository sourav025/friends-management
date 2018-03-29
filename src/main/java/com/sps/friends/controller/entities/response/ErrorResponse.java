package com.sps.friends.controller.entities.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"success","message"})
public class ErrorResponse extends Success{
    final private String message;
    public ErrorResponse(String message){
        this.message=message;
    }
}
