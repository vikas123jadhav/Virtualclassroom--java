package com.solugenix.Virtualclassroom.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoDataFoundException extends Exception{


    public NoDataFoundException(){
        super();
    }

    public NoDataFoundException(String msg){
        super(msg);
    }

    public NoDataFoundException(Exception exception){
        super(exception);
    }
}
