package com.hcmute.be_g2.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ErrorRes {
    private final String message;
    private final HttpStatus status;
}
