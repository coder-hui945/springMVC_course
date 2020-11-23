package com.wuminghui.exception;

//表示当用户年龄有异常时抛出
public class AgeException extends MyUserException{
    public AgeException() {
        super();
    }

    public AgeException(String message) {
        super(message);
    }
}
