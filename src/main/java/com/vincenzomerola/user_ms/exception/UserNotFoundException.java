package com.vincenzomerola.user_ms.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message ) {
        super(message);
    }


}
