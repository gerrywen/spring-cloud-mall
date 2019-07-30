package com.xinya.myexception;

public class MyException extends RuntimeException {
    public MyException(XyException exception) {
        super(exception.toString());
    }
}
