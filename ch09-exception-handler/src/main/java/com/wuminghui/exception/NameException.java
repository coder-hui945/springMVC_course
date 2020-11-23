package com.wuminghui.exception;

//表示当用户姓名有异常时抛出
public class NameException extends MyUserException {
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
