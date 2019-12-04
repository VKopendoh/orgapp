package com.vkopendoh.orgapp.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ViolationException extends ResponseStatusException {
    public ViolationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}