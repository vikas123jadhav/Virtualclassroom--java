package com.solugenix.Virtualclassroom.exceptions;

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
