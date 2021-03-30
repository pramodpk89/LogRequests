package com.ppk.requestLogger.model;

import lombok.Data;

@Data
public class Message {
    private String id;
    private String message;
    private String ipAddr;
    private String userAgent;
}
