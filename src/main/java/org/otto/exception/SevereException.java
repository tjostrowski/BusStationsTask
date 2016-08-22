package org.otto.exception;

/**
 * Created by tomek on 2016-08-20.
 */
public class SevereException extends RuntimeException {

    public SevereException(String msg) {
        super(msg);
    }

    public SevereException() {
    }
}
