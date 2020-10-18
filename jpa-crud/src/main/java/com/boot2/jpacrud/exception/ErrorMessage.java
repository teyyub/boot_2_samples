package com.boot2.jpacrud.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data 
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessage(int value, Date date, String message, String description) {
        statusCode = value;
        timestamp = date;
        message = message;
        description = description;
    }
}
