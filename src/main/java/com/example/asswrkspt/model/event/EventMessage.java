package com.example.asswrkspt.model.event;

import com.example.asswrkspt.common.utils.JSONUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EventMessage<T> {

    private T data;

    public EventMessage(T data) {
        this.data = data;
    }

    public String toJson(){
        return JSONUtils.toJson(data);
    }
}
