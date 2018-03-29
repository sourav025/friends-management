package com.sps.friends.controller.entities.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonPropertyOrder({"success","recipients","count"})
public class RecipientsList extends Counts{
    private final List<String> recipients;
    public RecipientsList(List<String> recipients){
        super(recipients.size());
        this.recipients=recipients;
    }
}
