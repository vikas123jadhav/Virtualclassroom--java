package com.solugenix.Virtualclassroom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NON_AUTHORITATIVE_INFORMATION)
public class InvalidCreadiantials extends Exception {

    public InvalidCreadiantials(String message) {
        super(message);
    }
}