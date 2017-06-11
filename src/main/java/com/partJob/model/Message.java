package com.partJob.model;

import lombok.Data;

/**
 * Created by t on 2017/3/26.
 */
@Data
public class Message {
    int code;
    String text;
    public Message(){}

    public Message(int code, String text) {
        this.code = code;
        this.text = text;
    }
}
