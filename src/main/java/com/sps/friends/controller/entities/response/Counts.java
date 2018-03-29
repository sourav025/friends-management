package com.sps.friends.controller.entities.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@JsonPropertyOrder({"success","count"})
public class Counts extends Success {
    final private int count;
    public Counts(int count){
        this.count=count;
    }
}
