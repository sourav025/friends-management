package com.sps.friends.controller.entities.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@JsonPropertyOrder({"success","recipients","count"})
public class RecipientsList extends Counts{
    private final List<String> recipients;
    public RecipientsList(List<String> recipients){
        super(recipients.size());
        this.recipients=recipients;
    }
}
