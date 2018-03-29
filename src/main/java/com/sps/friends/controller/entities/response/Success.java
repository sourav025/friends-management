package com.sps.friends.controller.entities.response;

import lombok.Getter;

@Getter
public class Success {
    final private boolean success;
    public Success(){
        this.success=true;
    }
    public Success(boolean success){
        this.success=success;
    }
}
