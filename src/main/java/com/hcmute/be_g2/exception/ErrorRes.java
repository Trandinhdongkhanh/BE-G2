package com.hcmute.be_g2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ErrorRes {
    private String message;
    private HttpStatus status;
}
